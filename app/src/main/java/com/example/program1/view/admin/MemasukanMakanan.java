package com.example.program1.view.admin;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.program1.R;
import com.example.program1.model.ModelMakanan;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MemasukanMakanan extends AppCompatActivity
{
  final String TAG = MemasukanMakanan.class.getSimpleName();
  EditText namaMakanan, hargaMakanan;
  ImageView fotoMakanan;
  Button tambah, batal;
  static final int PICK_IMAGE_REQUEST = 1;
  Uri mImageUri;
  ProgressBar mProgressBar;
  FirebaseAuth auth;
  FirebaseUser user;
  DatabaseReference dbRef;
  StorageReference mStorageRef;
  String uid;
  String namaMakanan1 = "", hargaMakanan1 = "";

  @Nullable
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_memasukan_makanan);
    auth = FirebaseAuth.getInstance();
    dbRef = FirebaseDatabase.getInstance().getReference();
    mStorageRef = FirebaseStorage.getInstance().getReference();
    namaMakanan = findViewById(R.id.input_namaMakanan);
    hargaMakanan = findViewById(R.id.input_hargaMakanan);
    fotoMakanan = findViewById(R.id.foto_admin_fotoMakanan);
    namaMakanan1 = getIntent().getStringExtra("namaMakanan");
    hargaMakanan1 = getIntent().getStringExtra("hargaMakanan");
    mProgressBar = findViewById(R.id.progress_bar);
    batal = findViewById(R.id.btn_batal);
    tambah = findViewById(R.id.btn_tambah);
    namaMakanan.setText(namaMakanan1);
    hargaMakanan.setText(hargaMakanan1);
    fotoMakanan.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v) {
        openFileChooser();
      }
    });

    tambah.setOnClickListener
     (
      new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          final String strnamaMakanan = namaMakanan.getText().toString().trim();
          final String strhargaMakanan = hargaMakanan.getText().toString().trim();
          boolean kirim = false;
          if (strnamaMakanan.isEmpty())
          {
            namaMakanan.requestFocus();
            namaMakanan.setError("Isi terlebih dahulu");
            kirim = false;
          }
          else { kirim = true; }
          if (strhargaMakanan.isEmpty())
          {
            hargaMakanan.requestFocus();
            hargaMakanan.setError("Isi terlebih dahulu");
            kirim = false;
          }
          else { kirim = true; }
          if (kirim)
          {
            if (mImageUri != null)
            {
              final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
              UploadTask uploadFoto = fileReference.putFile(mImageUri);
              Task<Uri> uploading = uploadFoto
              .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
              {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                  if (!task.isSuccessful())
                  {
                    Log.w(TAG, "Gagal upload foto ktp:" + task.getException());
                  }
                  return fileReference.getDownloadUrl();
                }
              })
              .addOnCompleteListener(new OnCompleteListener<Uri>()
              {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                  if (task.isSuccessful())
                  {
                    String urlDownload = task.getResult().toString();
                    System.out.println(mImageUri.toString());
                    ModelMakanan dataMakanan = new ModelMakanan(uid, strnamaMakanan, strhargaMakanan, urlDownload);
                    System.out.println(urlDownload + "coba2");
                    final DatabaseReference pushId = dbRef.child("foods");
                    pushId.push().setValue(dataMakanan);
                    DatabaseReference getId = FirebaseDatabase.getInstance().getReference().child("foods");
                    getId.addValueEventListener(new ValueEventListener()
                    {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                      {
                        for (DataSnapshot perData : dataSnapshot.getChildren())
                        {
                          ModelMakanan model = perData.getValue(ModelMakanan.class);
                          if (model.getNamaMakanan() != null)
                          {
                            if (model.getNamaMakanan().equalsIgnoreCase(strnamaMakanan))
                            {
                              System.out.println(model.getNamaMakanan());
                              String key = perData.getKey();
                              pushId.child(key).child("idMakanan").setValue(key);
                            }
                          }
                        }
                      }
                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) { }
                    });
                  }
                }
              });
            }
          }
          else { Log.w(TAG, "Gagal mengambil url admin"); }
        }
      });

      batal.setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View v)
        {
          AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
          builder.setTitle("ModelMakanan");
          builder.setMessage("Batal menambahkan admin?");
          builder.setCancelable(false);
          builder.setPositiveButton("Ya", new DialogInterface.OnClickListener()
          {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
              dialog.cancel();
              namaMakanan.setText("");
              hargaMakanan.setText("");
              fotoMakanan.setImageResource(R.drawable.bg_img_voucher);
            }
          });
        }
      }
     );
  }

  void openFileChooser()
  {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent, PICK_IMAGE_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
    {
      mImageUri = data.getData();
      Picasso.with(this).load(mImageUri).into(fotoMakanan);
    }
  }

  public String getFileExtension(Uri uri)
  {
    ContentResolver cR = getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(cR.getType(uri));
  }
}

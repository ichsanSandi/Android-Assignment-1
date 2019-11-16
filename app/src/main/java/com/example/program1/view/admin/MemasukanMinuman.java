package com.example.program1.view.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.program1.R;
import com.example.program1.model.ModelMinuman;
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

public class MemasukanMinuman extends AppCompatActivity 
{
     final String TAG = MemasukanMinuman.class.getSimpleName();
     EditText namaMinuman, hargaMinuman;
     ImageView fotoMinuman;
     Button tambah, batal;
     static final int PICK_IMAGE_REQUEST = 1;
     Uri mImageUri;
     ProgressBar mProgressBar;
     FirebaseAuth auth;
     FirebaseUser user;
     DatabaseReference dbRef;
     StorageReference mStorageRef;
     Uri uriFoto;
     String uid;

    @Nullable
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_memasukan_minuman);
        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        namaMinuman = findViewById (R.id.input_namaMinuman);
        hargaMinuman = findViewById (R.id.input_hargaMinuman);
        fotoMinuman = findViewById (R.id.foto_admin_fotoMinuman);
        mProgressBar = findViewById (R.id.progress_bar);
        batal = findViewById (R.id.btn_batal);
        tambah = findViewById (R.id.btn_tambah);

        fotoMinuman.setOnClickListener (new View.OnClickListener() 
        {
            @Override
            public void onClick (View v) 
            {
                openFileChooser();
            }
        });
        

        tambah.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                final String strnamaMinuman = namaMinuman.getText().toString().trim();
                final String strhargaMinuman = hargaMinuman.getText().toString().trim();
                boolean kirim = false;

                if (strnamaMinuman.isEmpty())
                {
                    namaMinuman.requestFocus();
                    namaMinuman.setError ("Isi terlebih dahulu");
                    kirim = false;
                }
                else
                {
                    kirim = true;
                }

                if (strhargaMinuman.isEmpty())
                {
                    hargaMinuman.requestFocus();
                    hargaMinuman.setError ("Isi terlebih dahulu");
                    kirim = false;
                }
                else
                {
                    kirim = true;
                }

                if (kirim)
                {
                    if (mImageUri != null)
                    {
//                        final StorageReference fileReference = mStorageRef.child (System.currentTimeMillis() + "." + getFileExtension (mImageUri));
                        final StorageReference fileReference = FirebaseStorage.getInstance().getReference ("fotoMakananMinuman").child (uriFoto.getLastPathSegment());

                        UploadTask uploadFoto = fileReference.putFile (mImageUri);

                        Task<Uri> uploading = uploadFoto.continueWithTask (new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() 
                        {
                            @Override
                            public Task<Uri> then (@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception 
                            {
                                if (!task.isSuccessful()) 
                                {
                                    Log.w (TAG, "Gagal upload foto ktp:" + task.getException());
                                }
                                return fileReference.getDownloadUrl();
                            }
                        }).addOnCompleteListener (new OnCompleteListener<Uri>() 
                        {
                            @Override
                            public void onComplete (@NonNull Task<Uri> task) 
                            {
                                if (task.isSuccessful()) 
                                {
                                    String urlDownload = task.getResult().toString();
                                    System.out.println (mImageUri.toString());
                                    ModelMinuman dataMinuman = new ModelMinuman (uid, strnamaMinuman, strhargaMinuman, urlDownload);
                                    System.out.println (urlDownload + "coba2");
                                    final DatabaseReference pushId = dbRef.child ("drinks");
                                    pushId.push().setValue (dataMinuman);
                                    DatabaseReference getId = FirebaseDatabase.getInstance().getReference().child ("drinks");
                                    getId.addValueEventListener (new ValueEventListener() {
                                        @Override
                                        public void onDataChange (@NonNull DataSnapshot dataSnapshot) 
                                        {
                                            for (DataSnapshot perData : dataSnapshot.getChildren()) 
                                            {
                                                ModelMinuman model = perData.getValue (ModelMinuman.class);
                                                if (model.getNamaMinuman() != null) 
                                                {
                                                    if (model.getNamaMinuman().equalsIgnoreCase (strnamaMinuman)) 
                                                    {
                                                        System.out.println (model.getNamaMinuman());
                                                        String key = perData.getKey();
                                                        pushId.child (key).child ("idMinuman").setValue (key);
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled (@NonNull DatabaseError databaseError) 
                                        {

                                        }
                                    });
                                    System.out.println (urlDownload + "coba");
                                }
                            }
                        });
                    }
                }
                else
                {
                    Log.w (TAG, "Gagal mengambil url admin");
                }
            }
        });



        batal.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick (View v) 
            {
                AlertDialog.Builder builder = new AlertDialog.Builder (getApplicationContext());
                builder.setTitle ("ModelMinuman");
                builder.setMessage ("Batal menambahkan admin?");
                builder.setCancelable (false);
                builder.setPositiveButton ("Ya", new DialogInterface.OnClickListener() 
                {
                    @Override
                    public void onClick (DialogInterface dialog, int which) 
                    {
                        dialog.cancel();
                        namaMinuman.setText ("");
                        hargaMinuman.setText ("");

                        fotoMinuman.setImageResource (R.drawable.bg_img_voucher);
                    }
                });
            }
        });
    }


     void openFileChooser() 
     {
        Intent intent = new Intent();
        intent.setType ("image/*");
        intent.setAction (Intent.ACTION_GET_CONTENT);
        startActivityForResult (intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) 
    {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) 
        {
            mImageUri = data.getData();
            Picasso.with (this).load (mImageUri).into (fotoMinuman);
        }
    }
}

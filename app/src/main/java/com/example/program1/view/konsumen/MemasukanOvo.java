package com.example.program1.view.konsumen;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.program1.R;
import com.example.program1.model.ModelMakanan;
import com.example.program1.model.Pengguna;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import com.squareup.picasso.Picasso;

public class MemasukanOvo extends AppCompatActivity {


    private final String TAG = MemasukanOvo.class.getSimpleName();

    private EditText poin, namaPengguna;
   private Button tambah, batal;

    //
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private StorageTask mUploadTask;
    private ProgressBar mProgressBar;
    private String mName;
    private String mImageUrl;
    //
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;
    private StorageReference mStorageRef;
    private Button buka_kamera;
    private Bitmap hasilFoto;
    private Uri uriFoto;
    private String uid;
    private String urlDownload;

    public static final String DIR_FOTO_Makanan = "FOTO_Makanan";

    public MemasukanOvo() {

    }

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memasukan_ovo);

        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        poin = findViewById(R.id.input_poinOvo);
        poin = findViewById(R.id.input_pengguna_ovo);

        batal = findViewById(R.id.btn_batal);
        tambah = findViewById(R.id.btn_tambah);
//

        //

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true) {

                }
                final String strNamaPengguna = namaPengguna.getText().toString().trim();
                final String strPoin = poin.getText().toString().trim();



                boolean kirim = false;

                if (strPoin.isEmpty()) {
                    poin.requestFocus();
                    poin.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }
                if (strPoin.isEmpty()) {
                    poin.requestFocus();
                    poin.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (kirim) {
                    DatabaseReference getId = FirebaseDatabase.getInstance().getReference().child("pengguna");
                    getId.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot perData : dataSnapshot.getChildren()) {
                                Pengguna model = perData.getValue(Pengguna.class);
                                if (model.getNama().equalsIgnoreCase(strNamaPengguna) ) {
                                    dbRef.child("pengguna").child(model.getId()).getRef().child("poin").setValue(strPoin).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(), "Data Poin Ditambah", Toast.LENGTH_LONG).show();

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });


        batal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("ModelMakanan");
                builder.setMessage("Batal menambahkan admin?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        poin.setText("");

                    }
                });
            }
        });
    }



//

}
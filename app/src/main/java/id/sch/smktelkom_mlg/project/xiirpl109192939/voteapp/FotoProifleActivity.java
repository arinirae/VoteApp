package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class FotoProifleActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_INTENT = 2;
    private Button btCamera, btNext, btGallery;
    private ImageView ivFoto;
    private FirebaseAuth firebaseAuth;
    private VoteAPI vp = new VoteAPI();
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_proifle);
        setTitle("Ambil Foto Profile");

        btCamera = (Button) findViewById(R.id.buttonCamera);
        btGallery = (Button) findViewById(R.id.buttonGallery);
        btNext = (Button) findViewById(R.id.buttonNext);
        ivFoto = (ImageView) findViewById(R.id.imageViewFoto);
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();

        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });

        btGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);*/
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_INTENT);
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            FirebaseUser user = firebaseAuth.getCurrentUser();

            vp.setImageRef("gs://voteapp-e3557.appspot.com/Profile/");
            Bitmap imageBitmap = data.getParcelableExtra("data");
            vp.uploadBitmapToFireStorage(imageBitmap, "profile_" + user.getUid() + ".jpg");
            ivFoto.setImageBitmap(imageBitmap);
            Toast.makeText(FotoProifleActivity.this, "Pendaftaran Sukses", Toast.LENGTH_SHORT).show();


//            Uri uri = data.getData();
//
//            StorageReference filepath = mStorage.child("Profile").child(uri.getLastPathSegment());
//
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    mProgress.dismiss();
//                    Toast.makeText(MainActivity.this, "Upload success", Toast.LENGTH_SHORT).show();
//                }
//            });
        } else if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {

                // Log.d(TAG, String.valueOf(bitmap));

                FirebaseUser user = firebaseAuth.getCurrentUser();

                vp.setImageRef("gs://voteapp-e3557.appspot.com/Profile/");
                //Bitmap imageBitmap = data.getParcelableExtra("data");
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                vp.uploadBitmapToFireStorage(bitmap, "profile_" + user.getUid() + ".jpg");
                ivFoto.setImageBitmap(bitmap);
                Toast.makeText(FotoProifleActivity.this, "Pendaftaran Sukses", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

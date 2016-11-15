package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.firebase.client.core.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class InputCanActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    ImageView mImageCan;
    EditText edNamaCan,edDeskCan;
    String nowCode = "-KVU0TrhkmVuBMmTedwz";
    VoteAPI vp = new VoteAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_can);
        vp.init("https://voteapp-e3557.firebaseio.com/",this);
        mImageCan = (ImageView) findViewById(R.id.imageViewInputCan);
        edNamaCan = (EditText) findViewById(R.id.editTextNamaInputCan);
        edDeskCan = (EditText) findViewById(R.id.editTextDeskripsiInputCan);
        findViewById(R.id.buttonCapCan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchCamera();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = data.getParcelableExtra("data");
            mImageCan.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
            mImageCan.setVisibility(View.VISIBLE);
        }
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        vp.addVoteCandidates(nowCode,edNamaCan.getText().toString(),edDeskCan.getText().toString(),imageEncoded);
    }
}

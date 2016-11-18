package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.firebase.client.core.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class InputCanActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    public static final String DESK_CAN = "DESK_CAN";
    public static final String NAMA_CAN = "NAMA_CAN";
    public static final String FURI_CAN = "FURI_CAN";
    ImageView mImageCan;
    EditText edNamaCan,edDeskCan;
    String nowCode = "";
    VoteAPI vp = new VoteAPI();
    FloatingActionButton fabAIC;
    int ke;
    String namapoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nowCode=getIntent().getStringExtra(AddCandidates.INVC);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_can);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vp.init("https://voteapp-e3557.firebaseio.com/",this);
        ke = Integer.parseInt(getIntent().getStringExtra(AddCandidates.CAN_CON));
        namapoto = "candidates_"+ ke +"_" + nowCode;

        mImageCan = (ImageView) findViewById(R.id.imageViewInputCan);
        edNamaCan = (EditText) findViewById(R.id.editTextNamaInputCan);
        edDeskCan = (EditText) findViewById(R.id.editTextDeskripsiInputCan);

        fabAIC = (FloatingActionButton) findViewById(R.id.fabAIC);
        fabAIC.setImageResource(R.drawable.ic_save_black_24dp);

        fabAIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.addVoteCandidates(nowCode,String.valueOf(ke),edNamaCan.getText().toString(),edDeskCan.getText().toString(),namapoto);
                Intent intento = new Intent();
                intento.putExtra(NAMA_CAN,edNamaCan.getText().toString());
                intento.putExtra(DESK_CAN,edDeskCan.getText().toString());
                intento.putExtra(FURI_CAN,namapoto);


                setResult(RESULT_OK,intento);
                finish();
            }
        });

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
            mImageCan.setVisibility(View.VISIBLE);
            vp.uploadBitmapToFireStorage(imageBitmap,namapoto);
        }
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intentc = new Intent();
            setResult(RESULT_CANCELED,intentc);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

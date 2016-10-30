package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    //deklarasi firebase database
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    //end deklarasi firebase database

    private EditText editTextName, editTextEmail;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Profile User");

        //inisialisasi firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        //end inisialisasi firebase database

        //inisialisasi icon back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //end inisialisasi icon back

        //inisialisasi
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        //end inisialisasi

        //agar button bisa di click
        buttonSave.setOnClickListener(this);
        //end agar button bisa di click

        //membuat variable email untuk mengambil data
        FirebaseUser email = firebaseAuth.getCurrentUser();
        editTextEmail.setText(email.getEmail());
        //end membuat variable email untuk mengambil data
    }


    //method untuk icon back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //end method untuk icon back


    //method untuk saveInformation
    private void saveUserInformation() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name, email);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child("userInformation").child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();
    }
    //end method untuk saveInformation


    @Override
    public void onClick(View view) {

        if (view == buttonSave) {
            saveUserInformation();
        }

    }


}

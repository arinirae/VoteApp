package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonUser;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Home");

        //inisialisasi
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonUser = (Button) findViewById(R.id.buttonProfile);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        //end inisialisasi

        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        //membuat variable user untuk mengambil data
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail.setText("Welcome " + user.getEmail());
        //end membuat variable user untuk mengambil data
        //end firebase

        //agar button bisa di click
        buttonLogout.setOnClickListener(this);
        buttonUser.setOnClickListener(this);
        //end agar button bisa di click
    }


    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == buttonUser) {
            startActivity(new Intent(this, UserActivity.class));
        }
    }

}

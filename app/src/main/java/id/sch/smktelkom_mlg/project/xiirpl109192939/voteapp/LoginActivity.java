package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        //inisialisasi
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);
        textViewSignup.setText(Html.fromHtml("Belum punya akun ?" + "<b>" + " Daftar" + "</b>"));
        progressDialog = new ProgressDialog(this);
        //end inisialisasi

        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), HomeVote.class));
        }
        //end firebase

        //agar button dan textView bisa di click
        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        //end agar button dan textView bisa di click
    }


    //method untuk login
    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //validasi form
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show();
            return;
        }
        //end validasi form

        //validasi form OK
        progressDialog.setMessage("Masuk...");
        progressDialog.show();
        //end validasi form OK

        //proses login, pencocokan data dengan firebase
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //membuka halaman profil setelah login
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeVote.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password atau Username salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //proses login, pencocokan data dengan firebase
    }
    //end method untuk login


    @Override
    public void onClick(View view) {
        if (view == buttonSignIn) {
            userLogin();
        }
        if (view == textViewSignup) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }


}

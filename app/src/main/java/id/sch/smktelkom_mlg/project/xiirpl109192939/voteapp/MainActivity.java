package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST_CODE = 1;
    VoteAPI vp = new VoteAPI();
    private ProgressDialog mProgress;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextNama, editTextRepassword;
    private RadioButton rbPria, rbWanita;
    private TextView textViewSignin, tvregis;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi
        tvregis = (TextView) findViewById(R.id.textViewRegis);
        mProgress = new ProgressDialog(this);
        rbPria = (RadioButton) findViewById(R.id.radioButtonPria);
        rbWanita = (RadioButton) findViewById(R.id.radioButtonWanita);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextRepassword = (EditText) findViewById(R.id.editTextRepassword);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        textViewSignin.setText(Html.fromHtml("Sudah punya akun ?" + "<b>" + " Masuk" + "</b>"));
        progressDialog = new ProgressDialog(this);
        //end inisialisasi

        //firebase
        Firebase.setAndroidContext(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        /*if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }*/
        //end firebase

        /*mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });*/

        //agar button dan textView bisa di click
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
        //end agar button dan textView bisa di click
    }


    //method radio
    private String radio() {
        String gender = "";
        if (rbPria.isChecked()) {
            gender = rbPria.getText().toString().trim();
        } else {
            gender = rbWanita.getText().toString().trim();
        }
        return gender;
    }
    //end method radio

    //method untuk daftar
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String repassword = editTextRepassword.getText().toString().trim();
        String nama = editTextNama.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String gender = radio();
        //validasi form
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Masukkan Nama Anda", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Masukkan Email Anda", Toast.LENGTH_SHORT).show();
            return;
        } else if (!email.matches(emailPattern)) {
            Toast.makeText(this, "Format Email Salah", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Masukkan Password Anda", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, "Masukkan kembali password Anda", Toast.LENGTH_SHORT).show();
            return;
        } else if (!(TextUtils.equals(password, repassword))) {
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //end validasi form OK
            progressDialog.setMessage("Proses ...");
            progressDialog.show();
            //end validasi form OK

            //penyimpanan data dalam firebase
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String email = editTextEmail.getText().toString().trim();
                                String name = editTextNama.getText().toString().trim();
                                String gender = radio();

                                UserInformation userInformation = new UserInformation(name, email, gender);

                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                databaseReference.child("userInformation").child(user.getUid()).setValue(userInformation);
                                startActivity(new Intent(getApplicationContext(), FotoProifleActivity.class));
                                Toast.makeText(MainActivity.this, "Proses...", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Proses Gagal", Toast.LENGTH_SHORT).show();
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    Log.e("WeakPass", e.getMessage());
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Log.e("InvalidAuth", e.getMessage());
                                } catch (FirebaseAuthUserCollisionException e) {
                                    Log.e("Collision", e.getMessage());
                                } catch (Exception e) {
                                    Log.e("Lainnya", e.getMessage());
                                }
                            }
                            progressDialog.dismiss();
                            finish();
                        }
                    });
            //end penyimpanan data dalam firebase
            //end validasi form
        }
    }
    //end method untuk daftar

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }

        if (view == textViewSignin) {
            //disini untuk membuka halaman login
            startActivity(new Intent(this, LoginActivity.class));
        }

    }


}


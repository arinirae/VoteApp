package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends Fragment {

    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonUser;
    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_hasil_vote, container, false);

        getActivity().setTitle("Home");

        //inisialisasi
        buttonLogout = (Button) view.findViewById(R.id.buttonLogout);
        buttonUser = (Button) view.findViewById(R.id.buttonProfile);
        textViewUserEmail = (TextView) view.findViewById(R.id.textViewUserEmail);
        //end inisialisasi

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            getActivity().finish();
            startActivity(new Intent(ProfileActivity.this.getActivity(), LoginActivity.class));
        }
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(ProfileActivity.this.getActivity(), LoginActivity.class));
            }
        });

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this.getActivity(), UserActivity.class));
            }
        });
        //membuat variable user untuk mengambil data
        /*FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail.setText("Welcome " + user.getEmail());*/
        //end membuat variable user untuk mengambil data
        //end firebase

        //agar button bisa di click
//        buttonLogout.setOnClickListener(this);
//        buttonUser.setOnClickListener(this);
        //end agar button bisa di click

    }
}

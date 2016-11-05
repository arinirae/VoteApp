package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class RiwayatVote extends Fragment  {

    //Firebase
    private Firebase mRef;
    private ArrayList<String> mMessages = new ArrayList<>();

    //UI
    private Spinner spDaftarRwt;
    private Button btnCek;

    public RiwayatVote (){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_riwayat_vote, container, false);

        spDaftarRwt = (Spinner) view.findViewById(R.id.spinnerDaftarRiwayat);
        btnCek=(Button) view.findViewById(R.id.buttonCek);
//        mRef= new Firebase("https://voteapp-e3557.firebaseio.com/vote");

//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    String category=postSnapshot.child("category").getValue().toString();
//                    if(category.equals(spDaftarRwt)) {
//                        Object j =postSnapshot.child("elements").getValue();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        view.findViewById(R.id.buttonCek).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RiwayatVote.this, HasilVote.class));
//            }
//        });
        return view;
    }


}

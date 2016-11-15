package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatFragment extends Fragment  {

    //Firebase
    private Firebase mRef;
    private ArrayList<String> daftarNama = new ArrayList<>();

    //UI
    private Spinner spDaftarRwt;
    private Button btnCek;
    private TextView tvTest;

    private VoteAPI spRiwayat = new VoteAPI();

    public RiwayatFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this.getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_riwayat_vote, container, false);

        getActivity().setTitle("Riwayat Voting");
        spDaftarRwt = (Spinner) view.findViewById(R.id.spinnerDaftarRiwayat);
        btnCek=(Button) view.findViewById(R.id.buttonCek);
        tvTest=(TextView) view.findViewById(R.id.textView);
        mRef = new Firebase("https://voteapp-e3557.firebaseio.com/vote");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Map<String, String> map = ds.getValue(Map.class);
                    daftarNama.add(map.get("nama"));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, daftarNama);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spDaftarRwt.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//
//        spRiwayat.setRef("https://voteapp-e3557.firebaseio.com/vote/-KVU0TrhkmVuBMmTedwz");
//        spRiwayat.fetchData();
//
//        btnCek.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tvTest.setText(spRiwayat.getData("nama"));
//            }
//        });

        return view;
    }



}

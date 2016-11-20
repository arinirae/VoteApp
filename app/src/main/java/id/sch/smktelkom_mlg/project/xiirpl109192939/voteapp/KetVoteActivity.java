package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

public class KetVoteActivity extends AppCompatActivity {
    VoteAPI vapi = new VoteAPI();
    TextView tvNama, tvDeskripsi;
    Button btnPilih;
    FirebaseDatabase fireDB = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Firebase mRef;
    private ArrayList<String> nama = new ArrayList<>();
    private ArrayList<String> deskripsi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_vote);

        String inv, pos;
        inv = getIntent().getStringExtra("INVITE_CODE");
        pos = getIntent().getStringExtra("POS");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        btnPilih = (Button) findViewById(R.id.btnPilih);
        tvNama = (TextView) findViewById(R.id.tvKetVotNama);
        tvDeskripsi = (TextView) findViewById(R.id.tvKetVotDeskripsi);

        ref = fireDB.getReference("vote/" + inv + "/pilihan");

        ref.child(pos).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Masuk", "onDataChange: MASUK");
                Candidates candidates = dataSnapshot.getValue(Candidates.class);

                /*Map<String,String> map = dataSnapshot.getValue(Map.class);
                tvNama.setText(map.get("nama"));
                tvDeskripsi.setText(map.get("deskripsi"));*/

                tvNama.setText(candidates.getNama());
                tvDeskripsi.setText(candidates.getDeskripsi());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                String Uid = user.getUid();

                databaseReference.child("log").child("id_Xfg4d").child(user.getUid()).setValue(true);
                Toast.makeText(KetVoteActivity.this, "Voting Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeVote.class));
            }
        });
        //mRef = new Firebase("https://voteapp-e3557.firebaseio.com/vote/id_Xfg4d/pilihan/") + getIntent().getStringExtra(SlideAdapter.);
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                tvNama = (TextView) findViewById(R.id.tvKetVotNama);
//                tvDeskripsi = (TextView) findViewById(R.id.tvKetVotDeskripsi);
//
//                Map<String,String> map = dataSnapshot.getValue(Map.class);
//                tvNama.setText(map.get("nama"));
//                tvDeskripsi.setText(map.get("deskripsi"));
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        vapi.init("https://voteapp-e3557.firebaseio.com",this);
//        vapi.newListenTo("https://voteapp-e3557.firebaseio.com/vote/id_Xfg4d/pilihan/0/nama");
//        vapi.newListenTo("https://voteapp-e3557.firebaseio.com/vote/id_Xfg4d/pilihan/0/deskripsi");
//        vapi.startListenerToTextView(0, (TextView) findViewById(R.id.tvKetVotNama));
//        vapi.startListenerToTextView(1, (TextView) findViewById(R.id.tvKetVotDeskripsi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

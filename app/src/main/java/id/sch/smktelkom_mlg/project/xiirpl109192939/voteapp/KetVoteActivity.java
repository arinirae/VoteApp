package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class KetVoteActivity extends AppCompatActivity {
    VoteAPI vapi = new VoteAPI();
    TextView tvNama, tvDeskripsi;
    private Firebase mRef;
    private ArrayList<String> nama = new ArrayList<>();
    private ArrayList<String> deskripsi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_vote);
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

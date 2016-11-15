package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter.CandidatesAdapter;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

public class AddCandidates extends AppCompatActivity {
    ArrayList<Candidates> mListCan = new ArrayList<>();
    ArrayList<Bitmap> poto = new ArrayList<>();
    CandidatesAdapter mAdapterCan;
    VoteAPI vp = new VoteAPI();
    String foto64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidates);
        vp.init("https://voteapp-e3557.firebaseio.com/vote/",this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewCan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapterCan = new CandidatesAdapter(mListCan);
        recyclerView.setAdapter(mAdapterCan);

        vp.newListenTo("https://voteapp-e3557.firebaseio.com/vote/-KVU0TrhkmVuBMmTedwz/pilihan/0/nama");
        vp.newListenTo("https://voteapp-e3557.firebaseio.com/vote/-KVU0TrhkmVuBMmTedwz/pilihan/0/deskripsi");
        vp.newListenTo("https://voteapp-e3557.firebaseio.com/vote/-KVU0TrhkmVuBMmTedwz/pilihan/0/foto");

        Firebase fotoref = new Firebase("https://voteapp-e3557.firebaseio.com/vote/-KVU0TrhkmVuBMmTedwz/pilihan/");
        fotoref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child("0").getChildren()) {
                    foto64 = ds.child("foto").getValue().toString();
                while(null == ds.child("foto").getValue().toString()) {
                    foto64 = ds.child("foto").getValue().toString();
                }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        try {
            fillDataCan();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillDataCan() throws IOException {

        mListCan.add(new Candidates("nama","coba",foto64));
        mAdapterCan.notifyDataSetChanged();
    }


}

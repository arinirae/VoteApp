package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter.CandidatesAdapter;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

public class AddCandidates extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD = 112;
    public static final String CAN_CON = "0";
    ArrayList<Candidates> mListCan = new ArrayList<>();
    ArrayList<Bitmap> poto = new ArrayList<>();
    CandidatesAdapter mAdapterCan;
    FloatingActionButton fabAC,fabNext;
    VoteAPI vp = new VoteAPI();
    String foto64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidates);
        fabAC = (FloatingActionButton) findViewById(R.id.fabAC);
        fabAC.setImageResource(R.drawable.ic_add_black_24dp);
        fabAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentadd = new Intent(getBaseContext(),InputCanActivity.class);
                intentadd.putExtra(CAN_CON,String.valueOf(vp.getIncrement()));
                vp.incrementAdd();
                startActivityForResult(intentadd, REQUEST_CODE_ADD);
            }
        });
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabNext.setImageResource(R.drawable.ic_add_black_24dp);
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabNext.setVisibility(View.GONE);


        vp.init("https://voteapp-e3557.firebaseio.com/vote/",this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewCan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapterCan = new CandidatesAdapter(mListCan);
        recyclerView.setAdapter(mAdapterCan);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_CANCELED) {
            vp.incrementSubstract();
            Toast.makeText(this, "Add Candidates Cancelled", Toast.LENGTH_SHORT).show();
            showHideFab();
            return;
        }else if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            mListCan.add(new Candidates(data.getStringExtra(InputCanActivity.NAMA_CAN),data.getStringExtra(InputCanActivity.DESK_CAN),data.getStringExtra(InputCanActivity.FURI_CAN)));
            Toast.makeText(this, "Candidates Added", Toast.LENGTH_SHORT).show();
            mAdapterCan.notifyDataSetChanged();
            showHideFab();
            return;
        }
    }

    public void showHideFab(){
        if(vp.getIncrement()>=2){
            fabNext.setVisibility(View.VISIBLE);
        }else{
            fabNext.setVisibility(View.GONE);
        }
        if(vp.getIncrement()>=10){
            fabAC.setVisibility(View.GONE);
        }
    }
}

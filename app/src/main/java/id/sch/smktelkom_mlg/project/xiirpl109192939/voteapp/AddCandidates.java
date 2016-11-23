package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter.CandidatesAdapter;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

public class AddCandidates extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD = 112;
    public static final String CAN_CON = "0";
    public static final String INVC = "INVC";
    public static final int REQUEST_CODE_INVC = 153;
    ArrayList<Candidates> mListCan = new ArrayList<>();
    ArrayList<Bitmap> poto = new ArrayList<>();
    CandidatesAdapter mAdapterCan;
    //FloatingActionButton fabAC;
    FloatingActionButton fabNext;
    String nowCode;
    TextView tvCan;
    FloatingActionButton fabAC;
    VoteAPI vp = new VoteAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidates);
        nowCode = getIntent().getStringExtra(AddVote.INVC);
        tvCan = (TextView) findViewById(R.id.tvCandidates);
        fabAC = (FloatingActionButton) findViewById(R.id.fabAC);
        fabAC.setImageResource(R.drawable.ic_add_black_24dp);
        fabAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.incrementAdd();
                Intent intentadd = new Intent(getBaseContext(),InputCanActivity.class);
                intentadd.putExtra(INVC,nowCode);
                intentadd.putExtra(CAN_CON,String.valueOf(vp.getIncrement()));
                startActivityForResult(intentadd, REQUEST_CODE_ADD);
            }
        });
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabNext.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vp.getIncrement()>=2){
                Intent intentinvc = new Intent(getBaseContext(), InvcActivity.class);
                intentinvc.putExtra(INVC,nowCode);
                startActivityForResult(intentinvc, REQUEST_CODE_INVC);}
                else{
                    Toast.makeText(AddCandidates.this, "You must add more candidates", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vp.init("https://voteapp-e3557.firebaseio.com/vote/",this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewCan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapterCan = new CandidatesAdapter(this,mListCan);
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
            vp.addVoteCandidates(nowCode,String.valueOf(vp.getIncrement()),data.getStringExtra(InputCanActivity.NAMA_CAN),data.getStringExtra(InputCanActivity.DESK_CAN),data.getStringExtra(InputCanActivity.FURI_CAN));
            Toast.makeText(this, "Candidates Added", Toast.LENGTH_SHORT).show();
            mAdapterCan.notifyDataSetChanged();
            showHideFab();
            return;
        }else if(requestCode == REQUEST_CODE_INVC && resultCode == RESULT_OK){
            Intent intentback = new Intent();
            setResult(RESULT_OK, intentback);
            finish();
        }
    }

    public void showHideFab(){
        if(vp.getIncrement()==1){
            tvCan.setText("Add minimal 2 candidates");
        }
        if(vp.getIncrement()>=2){
        tvCan.setVisibility(View.GONE);
        }
        if(vp.getIncrement()>=10){
            fabAC.setVisibility(View.GONE);
        }
    }
}

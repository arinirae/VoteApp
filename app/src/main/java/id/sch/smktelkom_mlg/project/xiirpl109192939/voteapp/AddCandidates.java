package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter.CandidatesAdapter;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

public class AddCandidates extends AppCompatActivity {
    ArrayList<Candidates> mListCan = new ArrayList<>();
    CandidatesAdapter mAdapterCan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidates);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewCan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapterCan = new CandidatesAdapter(mListCan);
        recyclerView.setAdapter(mAdapterCan);
        fillDataCan();
    }

    private void fillDataCan() {

    }
}

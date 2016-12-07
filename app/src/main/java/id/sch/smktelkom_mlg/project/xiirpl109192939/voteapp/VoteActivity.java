package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter.VoteAdapter;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Vote;

public class VoteActivity extends AppCompatActivity {

//    ViewPager viewPager;
//    SlideAdapter custom;
//    String invcode;

    ArrayList<Vote> mListCan = new ArrayList<>();
    VoteAdapter mAdapterCan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote2);
//        invcode = getIntent().getStringExtra(VoteFragment.INVC);
//        viewPager = (ViewPager) findViewById(R.id.ViewPager);
//        custom = new SlideAdapter(this,invcode);
//        viewPager.setAdapter(custom);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewVote);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapterCan = new VoteAdapter(this, mListCan);
        recyclerView.setAdapter(mAdapterCan);
    }
}

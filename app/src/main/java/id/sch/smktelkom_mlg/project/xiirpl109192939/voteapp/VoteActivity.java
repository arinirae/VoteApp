package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class VoteActivity extends AppCompatActivity {

    ViewPager viewPager;
    SlideAdapter custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote2);

        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        custom = new SlideAdapter(this);
        viewPager.setAdapter(custom);

    }
}
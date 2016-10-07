package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HasilVote extends AppCompatActivity {
    VoteAPI vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_vote);
        vp.setRef("https://voteapp-e3557.firebaseio.com/");

    }
}

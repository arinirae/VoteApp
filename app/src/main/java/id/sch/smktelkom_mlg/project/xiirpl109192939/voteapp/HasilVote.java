package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HasilVote extends AppCompatActivity {
    TextView tvHasilVote, tvDaftarCalon, tvJumSuara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_vote);

        tvHasilVote = (TextView) findViewById(R.id.textViewHasil);
        tvDaftarCalon = (TextView) findViewById(R.id.textViewNamaCalon);
        tvJumSuara= (TextView) findViewById(R.id.textViewJumSuara);
    }
}

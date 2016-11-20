package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class HasilActivity extends AppCompatActivity  {

    TextView tvJudulVote, tvnamaCalon, tvjumSuara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_vote);
        tvJudulVote = (TextView) findViewById(R.id.textViewJudul);
        tvnamaCalon = (TextView) findViewById(R.id.textViewNamaCalon);
        tvjumSuara = (TextView) findViewById(R.id.textViewJumSuara);

        Bundle bundle = getIntent().getExtras();
        String judulVote = bundle.getString("getData");

        tvJudulVote.setText(judulVote);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId()==startActivityFromFragment(RiwayatFragment.class);)
//        {
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }

    @Override
    public void onResume() {
        setTitle("Hasil Voting");
        super.onResume();
    }



}

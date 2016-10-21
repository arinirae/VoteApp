package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

public class RiwayatVote extends AppCompatActivity {

    Spinner spDaftarRwt;
    Button btnCek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_vote);

        spDaftarRwt = (Spinner) findViewById(R.id.spinnerDaftarRiwayat);
        btnCek=(Button) findViewById(R.id.buttonCek);
    }
}

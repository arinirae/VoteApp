package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        tvHasil.setText("gandhy");
    }
}

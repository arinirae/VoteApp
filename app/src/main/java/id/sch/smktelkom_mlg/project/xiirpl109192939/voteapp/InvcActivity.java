package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InvcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invc);
        ((EditText)findViewById(R.id.editTextinvc)).setText(getIntent().getStringExtra(AddCandidates.INVC));
        Button byik = (Button)findViewById(R.id.buttonYIK);
        byik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenback = new Intent();
                setResult(RESULT_OK,intenback);
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intenback = new Intent();
            setResult(RESULT_OK,intenback);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

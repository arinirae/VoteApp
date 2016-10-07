package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

public class AddVote extends AppCompatActivity {
    Button cbtn;
    EditText ednama,edtime;
    CheckBox cbPriv,cbNeed;
    Spinner spCc;
    VoteAPI vp = new VoteAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vote);
        vp.init("https://voteapp-e3557.firebaseio.com",this);
        ednama = (EditText) findViewById(R.id.edVoteName);
        edtime = (EditText) findViewById(R.id.edTime);
        spCc = (Spinner) findViewById(R.id.spinner);
        cbPriv = (CheckBox) findViewById(R.id.checkPrivate);
        cbNeed = (CheckBox) findViewById(R.id.checkNeedApp);
        cbtn = (Button) findViewById(R.id.btnCreate);
        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean cbn = false,cbp = false;
                if(cbPriv.isChecked())cbp = true;
                if(cbNeed.isChecked())cbn = true;
                String nama = ednama.getText().toString();
                String wktu = "26-05-1999 12:32:11";
                vp.addVote(nama,Integer.parseInt(edtime.getText().toString()),cbn,cbp,wktu);
            }
        });
    }
}

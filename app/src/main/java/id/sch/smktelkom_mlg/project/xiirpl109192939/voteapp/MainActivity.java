package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tvHasil;
    public  VoteAPI vot = new VoteAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvHasil.setText(vot.getChildData("email"));
            }
        });


    }

    @Override
    protected void onStart(){
        super.onStart();
        vot.setRef("https://voteapp-e3557.firebaseio.com/user/");
        vot.fetchData();
        vot.fetchDataChild(vot.getKey(vot.findKeyIndex("0")));
        tvHasil.setText(vot.getChildData("username"));


    }



}


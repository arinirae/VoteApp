package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HasilVote extends Fragment {

    public HasilVote () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_hasil_vote, container, false);


        return view;
    }


}

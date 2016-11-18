package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VoteFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_vote, container, false);

        view.findViewById(R.id.btnMasuk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), VoteActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;

    }




    //
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vote);
//
//        findViewById(R.id.button_ket).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(VoteFragment.this, KetVoteActivity.class));
//            }
//        });
//
//        viewPager = (ViewPager) findViewById(R.id.ViewPager);
//        custom = new SlideAdapter(this);
//        viewPager.setAdapter(custom);
}

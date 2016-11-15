package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VoteFragment extends Fragment {

    ViewPager viewPager;
    SlideActivity custom;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_vote, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.ViewPager);
        custom = new SlideActivity(this.getActivity());
        viewPager.setAdapter(custom);

        return view;
    }
    @Override
    public void onResume() {
        getActivity().setTitle("Voting");
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.button_ket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), KetVoteActivity.class));
            }
        });

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
//        custom = new SlideActivity(this);
//        viewPager.setAdapter(custom);
}

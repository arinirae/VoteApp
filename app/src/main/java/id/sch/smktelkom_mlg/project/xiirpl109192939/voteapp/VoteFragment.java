package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VoteFragment extends Fragment {

    public static final String INVC = "INVC";
    VoteAPI vpvf;
    View view;
    EditText edInvcJ;
FirebaseAuth firebaseAuth;
    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getActivity().getBaseContext());
        firebaseAuth = FirebaseAuth.getInstance();
 user = firebaseAuth.getCurrentUser();
        vpvf = new VoteAPI();
        view = inflater.inflate(R.layout.activity_vote, container, false);
        vpvf.setRef("https://voteapp-e3557.firebaseio.com/vote/");
        vpvf.fetchData();

        edInvcJ = (EditText) view.findViewById(R.id.editTextInvite);


        view.findViewById(R.id.btnMasuk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edInvcJ.getText().toString().isEmpty()){
                    edInvcJ.setError("Insert Vote Invitation Code");
                }else {
gotoVote();
                }
            }
        });


        return view;

    }


public void gotoVote(){
    if(vpvf.findKey(edInvcJ.getText().toString())) {
        vpvf.fetchDataChild(edInvcJ.getText().toString());
        Intent intent = new Intent(view.getContext(), VoteActivity.class);
        intent.putExtra(INVC,edInvcJ.getText().toString().trim());
        vpvf.addVoteUser(edInvcJ.getText().toString(),user.getUid(),vpvf.getChildData("needapprove"),"false");
        view.getContext().startActivity(intent);

    }else if (null==vpvf.getKey(0)) {
        Toast.makeText(getActivity().getBaseContext(), "Loading data... Please Try Again", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(getActivity().getBaseContext(), "There is no vote using that code", Toast.LENGTH_SHORT).show();
    }
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

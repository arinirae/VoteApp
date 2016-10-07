package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class HasilFragment extends Fragment  {

    TextView tvJudulVote, tvnamaCalon, tvjumSuara;

    public HasilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_hasil_vote, container, false);


        tvJudulVote = (TextView) view.findViewById(R.id.textViewJudul);
        tvnamaCalon = (TextView) view.findViewById(R.id.textViewNamaCalon);
        tvjumSuara = (TextView) view.findViewById(R.id.textViewJumSuara);
        return view;
    }

    @Override
    public void onResume() {
        getActivity().setTitle("Hasil Voting");
        super.onResume();
    }

    /*-- VARIABLE --*/
    public Firebase ref;
    public HashMap<String,Object> data = new HashMap<String ,Object>();
    public HashMap<Integer, String> datakey = new HashMap<Integer, String>();
    public HashMap<String,Object> datachild = new HashMap<String, Object>();
    public HashMap<Integer,String> datachildkey = new HashMap<Integer, String>();
    public int listenercount = 0;
    public HashMap<Integer,Firebase> listener = new HashMap<Integer, Firebase>();

    /*-- CONSTRUCT --*/
    public void setRef(String refx){
        this.ref = new Firebase(refx);
    }
    public void fetchData(){
        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int inc=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    data.put(ds.getKey(),ds.getValue());
                    datakey.put(inc,ds.getKey());
                    inc++;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}

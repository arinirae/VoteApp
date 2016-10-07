package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

/**
 * Created by vergie on 25/10/16.
 */
public class VoteAPI {
    public Firebase ref;
    public HashMap<String,Object> data = new HashMap<String ,Object>();
    public HashMap<Integer, String> datakey = new HashMap<>();

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
    public String getData(String what){
        String dts ="Loading ...";
        if (null == data.get(what)){
        //do nothing
        }else{
            dts = data.get(what).toString();
        }
        return dts;
    }
    public String getKey(Integer index){
        String dts ="Loading ...";
        if (null == datakey.get(index)){
            //do nothing
        }else{
            dts = datakey.get(index).toString();
        }
        return dts;
    }
    public String findKey(String what){
        String res = "Not Found";
        for (int i=0;i<datakey.size();i++) {
        if(datakey.get(i) == what){ res = datakey.get(i);}
        }
        return res;
    }
    public int findKeyIndex(String what){
        int res = -1;
        for (int i=0;i<datakey.size();i++) {
            if(datakey.get(i) == what){ res = i;}
        }
        return res;
    }
    public void destroyData(){
        this.data.clear();
        this.datakey.clear();
    }
    public void setValue(String username,String email,String password){
    this.ref.push().setValue(new UserData(username, email, password));
    }
    private static class UserData{
        private String username,email,password;
        public UserData(String username, String email,String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
}



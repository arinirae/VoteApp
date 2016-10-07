package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Context;
import android.util.Base64;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by vergie on 25/10/16.
 */
public class VoteAPI {

    /*-- VARIABLE --*/
    public Firebase ref;
    public HashMap<String,Object> data = new HashMap<String ,Object>();
    public HashMap<Integer, String> datakey = new HashMap <Integer, String>();
    public HashMap<String,Object> datachild = new HashMap<String, Object>();
    public HashMap<Integer,String> datachildkey = new HashMap<Integer, String>();
    public int listenercount = 0;
    public HashMap<Integer,Firebase> listener = new HashMap<Integer, Firebase>();
    public boolean isDataLoaded = false;
    /*-- CONSTRUCT --*/
    public void init(String refx,Context context){
        Firebase.setAndroidContext(context);
        this.ref = new Firebase(refx);
    }
    public void setRef(String refx){
        this.ref = new Firebase(refx);
    }

    /*-- FETCH FUNCTION --*/
    public void fetchData(){
        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int inc=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                 String isi = ds.getValue(String.class) == null?"Loading...":ds.getValue(String.class);
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
    public void fetchDataChild(String children){
        this.ref.child(children).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int inc=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    datachild.put(ds.getKey(),ds.getValue());
                    datachildkey.put(inc,ds.getKey());
                    inc++;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    /*-- GET FUNCTION --*/
    public String getData(String what){
        String dts ="null";
        if (null == data.get(what)){
        //do nothing
            getData(what);
        }else{
            dts = data.get(what).toString();
        }
        return dts;
    }
    public String getKey(Integer index){
        String dts ="null";
        if (null == datakey.get(index)){
            //do nothing
        }else{
            dts = datakey.get(index).toString();
        }
        return dts;
    }
    public String getChildData(String what){
        String dts ="null";
        if (null == datachild.get(what)){
            //do nothing
            getChildData(what);
        }else{
            dts = datachild.get(what).toString();
        }
        return dts;
    }
    public String getChildKey(Integer index){
        String dts ="null";
        if (null == datachildkey.get(index)){
            //do nothing
        }else{
            dts = datachildkey.get(index).toString();
        }
        return dts;
    }
    public int getListenerCount(){
        return this.listenercount;
    }

    /*-- FIND FUNCTION --*/
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
    public String findChildKey(String what){
        String res = "Not Found";
        for (int i=0;i<datachildkey.size();i++) {
            if(datachildkey.get(i) == what){ res = datachildkey.get(i);}
        }
        return res;
    }
    public int findChildKeyIndex(String what) {
        int res = -1;
        for (int i = 0; i < datachildkey.size(); i++) {
            if (datachildkey.get(i) == what) {
                res = i;
            }
        }
        return res;
    }

    /*-- SET FUNCTION --*/
    public void setValUseLink(String ref,String changeto){
        Firebase refs = new Firebase(ref);
        refs.setValue(changeto);
    }

    /*-- LISTEN FUNCTION --*/
    public void newListenTo(String ref){
    listener.put(listenercount,new Firebase(ref));
    listenercount +=1;
    }
    public void startListenerToTextView(Integer ke, final TextView txtv){
        if(null == listener.get(ke)){
            txtv.setText("no data to listen");
        }else {
            listener.get(ke).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(null == dataSnapshot.getValue(String.class)){txtv.setText("null");}else{txtv.setText(dataSnapshot.getValue(String.class));}
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }

    /*-- ADD FUNCTION --*/
    public void addUser(String username,String email,String password){
        this.ref.push().setValue(new UserData(username, email, password));
    }
    public void addVote(String namanya,int durasinya,boolean needapprove,boolean privat,String startfromnya){
        this.ref.child("vote").push().child(getNewInvCode()).setValue(new VoteData(namanya,durasinya,needapprove,privat,startfromnya));
    }

    /*-- CUSTOM FUNCTION --*/
    public String getNewInvCode(){
        String invcode = "" ;
        Long milis = System.currentTimeMillis()/1000L;
        String milo = encodeString(milis.toString());
        return invcode;
    }

    /*-- DATA TYPE --*/
    private static class UserData{
        private String username,email,password;
        public UserData(String username, String email,String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
    private static class VoteData{
        String nama,startfrom;
        int durasi;
        boolean needapprove,privat;
        public VoteData(String nama, Integer durasi,boolean needapprove,boolean privat,String startfrom) {
            this.nama = nama ;
            this.durasi = durasi;
            this.needapprove = needapprove;
            this.privat = privat;
            this.startfrom = startfrom;
        }
    }

    /*-- DESTROY --*/
    public void destroy(){
        this.ref = new Firebase("");
        this.data.clear();
        this.datakey.clear();
        this.datachild.clear();
        this.datachildkey.clear();
    }


    /*-- BASE64 FUNCTION --*/
    private String encodeString(String s) {
        byte[] data = new byte[0];

        try {
            data = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            String base64Encoded = Base64.encodeToString(data, Base64.DEFAULT);
            return base64Encoded;

        }
    }


}

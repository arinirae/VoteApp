package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

/**-KVU0TrhkmVuBMmTedwz
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
    public String lastinvc="";
    public String changestr="";
    private String imageStorageUrl="gs://voteapp-e3557.appspot.com/image/";
    public HashMap<String, String> storage = new HashMap <String, String>();
    public int increment =0 ;

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
        String dts ="loading...";
        if (null == data.get(what)){
        //do nothing
            dts = getData(what);
        }else{
            dts = data.get(what).toString();
        }
        return dts;
    }
    public String getKey(Integer index){
        String dts ="null";
        if (null == datakey.get(index)){
            //do nothing
            getKey(index);
        }else{
            dts = datakey.get(index).toString();
        }
        return dts;
    }
    public String getChildData(String what){
        String dts ="loading...";
        if (null == datachild.get(what)){
            //do nothing
            getChildData(what);
        }else{
            dts = datachild.get(what).toString();
        }
        return dts;
    }
    public String getChildKey(Integer index){
        String dts ="loading...";
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

    public String getLastInvCode(){
        return lastinvc;
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

    /*-- STORE FUNCTION --*/
    public void store(String key,String value){
        storage.put(key,value);
    }
    public String getStored(String key){
        return storage.get(key);
    }
    public void incrementAdd(){
        increment+=1;
    }
    public void incrementSubstract(){
        increment+=1;
    }
    public int getIncrement(){
        return increment;
    }

    /*-- ADD FUNCTION --*/
    public void addUser(String username,String email,String password){
        this.ref.push().setValue(new UserData(username, email, password));
    }
    public void addVote(String namanya,int durasinya,boolean needapprove,boolean privat,String startfromnya){
        this.ref.child("vote").push().child(getNewInvCode()).setValue(new VoteData(namanya,durasinya,needapprove,privat,startfromnya));
        this.ref.child("vote").child(getLastInvCode()).child("pilihan").child("count").setValue("0");
    }
    public void addVoteCandidates(String invc,String child,String namanya,String deskripsinya,String fotonya){
        this.ref.child("vote").child(invc).child("pilihan").child(child).setValue(new Candidates(namanya,deskripsinya,fotonya));
    }
    /*-- CUSTOM FUNCTION --*/
    public String getNewInvCode(){
        String invcode = "" ;
        Long milis = System.currentTimeMillis()/1000L;
        String milo = encodeString(milis.toString());
        this.lastinvc = invcode;
        return invcode;
    }
    public String getCanCount(){
        HashMap<Integer, String> datakeyC = datakey;
        if(datakeyC != null) {
            int count = datakeyC.size();
            return String.valueOf(count-1);
        }else{
            return "0";
        }
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

    /*-- STORAGE FUNCTION --*/
    public void uploadBitmapToFireStorage(Bitmap bitmap,String fileName){
        FirebaseStorage fsref = FirebaseStorage.getInstance();
        StorageReference ref = fsref.getReferenceFromUrl(this.imageStorageUrl);
        // Get the data from an ImageView as bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = ref.child(fileName).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
    }


}


/*-- Trash Function--*/
//public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//    String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//    vp.addVoteCandidates(nowCode,edNamaCan.getText().toString(),edDeskCan.getText().toString(),imageEncoded);
//}

//    public String startListenerToString(Integer ke,String cstr){
//        if(null == listener.get(ke)){
//            this.changestr = "no data to listen";
//        }else {
//            listener.get(ke).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if(null == dataSnapshot.getValue(String.class)){cstr = "null";}else{retdat[0] = dataSnapshot.getValue(String.class);}
//                }
//
//                @Override
//                public void onCancelled(FirebaseError firebaseError) {
//
//                }
//            });
//
//        }
//        return this.changestr;
//    }

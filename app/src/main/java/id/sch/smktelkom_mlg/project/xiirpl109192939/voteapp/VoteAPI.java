package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.UserVote;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.VoteData;

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
    public HashMap<String, String> storage = new HashMap <String, String>();
    public int increment =0 ;
    private String imageStorageUrl = "gs://voteapp-e3557.appspot.com/image/";

    /*-- CONSTRUCT --*/
    public void init(String refx,Context context){
        Firebase.setAndroidContext(context);
        this.ref = new Firebase(refx);
    }
    public void setRef(String refx){
        this.ref = new Firebase(refx);
    }

    public void setImageRef(String refx) {
        this.imageStorageUrl = refx;
    }
    /*-- FETCH FUNCTION --*/
    public void fetchData(){
        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int inc=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
data.put(ds.getKey(),ds.getValue().toString());
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
    public int getDataCount(){
        return datakey.size();
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
        return this.lastinvc;
    }


    /*-- FIND FUNCTION --*/
    public boolean findKey(String what){
        boolean res = false;
        for (int i=0;i<datakey.size();i++) {
        if(this.datakey.get(i).equals(what)){ res = true;}
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
    public void startListenerToImageView(final String urlImg, final Context context, final Integer ke, final ImageView txiv) {
        if (null == listener.get(ke)) {
            txiv.setImageResource(R.drawable.ic_portrait_black_24dp);
        } else {
            listener.get(ke).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (null == dataSnapshot.getValue(String.class)) {
                        txiv.setImageResource(R.drawable.ic_portrait_black_24dp);
                    } else {
                        //txiv.setText(dataSnapshot.getValue(String.class));
                        FirebaseStorage fsref = FirebaseStorage.getInstance();
                        StorageReference ref = fsref.getReferenceFromUrl(urlImg);
//                        "users/me/profile.png"
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // Got the download URL for 'users/me/profile.png'
                                // Pass it to Picasso to download, show in ImageView and caching
                                Picasso.with(context).load(uri.toString()).into(txiv);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });
                    }
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
    public void addVoteUser(String invc,String id,String approved,String sudahpilih){
        Firebase avuref = new Firebase("https://voteapp-e3557.firebaseio.com/vote/");
        avuref.child(invc).child("vote_user").child(id).setValue(new UserVote(approved,sudahpilih));
    }
    public void addVote(String namanya,int durasinya,boolean needapprove,boolean privat,String startfromnya){
        Map<String,String> pilihan = new HashMap<String,String>();
        pilihan.put("count","0");
        Map<String,String> uservote = new HashMap<String,String>();
        uservote.put("count","0");
        VoteData vd = new VoteData(namanya,durasinya,needapprove,privat,startfromnya,pilihan,uservote);
        Firebase rc = this.ref.child("vote").push();
        rc.setValue(vd);
        this.lastinvc = rc.getKey();

    }
    public void addVoteCandidates(String invc,String child,String namanya,String deskripsinya,String fotonya){
        this.ref.child("vote").child(invc).child("pilihan").child(child).setValue(new Candidates(namanya,deskripsinya,fotonya));
    }
    /*-- CUSTOM FUNCTION --*/
    public String getNewInvCode(){
        String invcode = "" ;
        Long milis = System.currentTimeMillis()/1000L;
        String milo = encodeString(milis.toString());
        invcode = milo ;
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

    /*-- DATA TYPE --*/
    private static class UserData {
        private String username, email, password;

        public UserData(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
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

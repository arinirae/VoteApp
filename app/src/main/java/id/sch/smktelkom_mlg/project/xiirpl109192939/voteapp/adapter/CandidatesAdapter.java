package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.R;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

/**
 * Created by vergie on 14/11/16.
 */
public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.ViewHolder> {
    ArrayList<Candidates>   candidatelist;
    private Context context;

    public CandidatesAdapter(ArrayList<Candidates> mListCan) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_list,parent,false);
        ViewHolder vn = new ViewHolder(v);
        return vn;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Candidates candi = candidatelist.get(position);
        holder.tvNamaCan.setText(candi.nama);
        holder.tvDeskripsiCan.setText(candi.deskripsi);


        FirebaseStorage fsref = FirebaseStorage.getInstance();
        StorageReference ref = fsref.getReferenceFromUrl("gs://voteapp-e3557.appspot.com/image/");
        ref.child(candi.foto).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context).load(uri.toString()).into(holder.ivCan);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                holder.tvDeskripsiCan.setText(candi.foto);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(candidatelist!=null)
            return candidatelist.size();
        return 0;
    }

    public CandidatesAdapter(Context context, ArrayList<Candidates> candidatelist){
        this.context = context;
        this.candidatelist = candidatelist;
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCan;
        TextView tvNamaCan;
        TextView tvDeskripsiCan;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCan =(ImageView)  itemView.findViewById(R.id.imageViewCandidates);
            tvNamaCan = (TextView) itemView.findViewById(R.id.textViewJudulCan);
            tvDeskripsiCan= (TextView) itemView.findViewById(R.id.textViewDeskripsiCan);
        }
    }

}

package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.R;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Candidates;

/**
 * Created by vergie on 14/11/16.
 */
public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.ViewHolder> {
    ArrayList<Candidates>   candidatelist;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_list,parent,false);
        ViewHolder vn = new ViewHolder(v);
        return vn;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    Candidates candi = candidatelist.get(position);
        holder.tvNamaCan.setText(candi.nama);
        holder.tvDeskripsiCan.setText(candi.deskripsi);
        Bitmap foto = null;
//        try {
//
//           foto = decodeFromFirebaseBase64(candi.foto);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if (foto != null) {
//            holder.ivCan.setImageBitmap(foto);
            holder.tvDeskripsiCan.setText("gak null");
        }else{
        holder.tvDeskripsiCan.setText(candi.foto);
        }
    }

    @Override
    public int getItemCount() {
        if(candidatelist!=null)
            return candidatelist.size();
        return 0;
    }

    public CandidatesAdapter(ArrayList<Candidates> candidatelist){
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

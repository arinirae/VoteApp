package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        holder.ivCan.setImageDrawable(candi.foto);
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

package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.R;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.VoteActivity;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Vote;

/**
 * Created by Arini on 07/12/2016.
 */

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder> {
    ArrayList<Vote> voteList;
    private Context context;

    public VoteAdapter(VoteActivity context, ArrayList<Vote> mListCan) {
        this.context = context;
        this.voteList = voteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_list, parent, false);
        ViewHolder vn = new ViewHolder(v);
        return vn;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Vote vote = voteList.get(position);
        holder.tvNamaCan.setText(vote.getNama());

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCan;
        TextView tvNamaCan;
        TextView tvDeskripsiCan;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCan = (ImageView) itemView.findViewById(R.id.imageViewVote);
            tvNamaCan = (TextView) itemView.findViewById(R.id.textViewJudulVote);
            tvDeskripsiCan = (TextView) itemView.findViewById(R.id.textViewDeskripsiVote);
        }
    }

}

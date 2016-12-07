package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.Vote;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.model.VoteData;

public class SlideAdapter extends PagerAdapter {

    public static final String POS = "0";
    public int[] imageResources =
            {R.drawable.capture1, R.drawable.capture2, R.drawable.capture3, R.drawable.capture4, R.drawable.capture5, R.drawable.capture6};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private String invc;
    String ingkod = "";
    VoteAPI vp ;
    TextView tvnmc;
    Integer nowpos;
    ImageButton ib;
    Handler mhandler = new Handler();
    Vote vdl ;
    public SlideAdapter(Context c , String invc) {
        ctx = c;
        this.invc = invc;
        Toast.makeText(c, invc, Toast.LENGTH_SHORT).show();

        vp = new VoteAPI();
        vp.setRef("https://voteapp-e3557.firebaseio.com/vote/"+ invc);
        vp.fetchDataTo("VotingData");
        mhandler.postDelayed(new Runnable() {
            public void run() {
               vdl = vp.getDataVoting();
            }
        }, 2500);
    }

    @Override
    public int getCount() {
        return imageResources.length;
    }


    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.activity_slide, container, false);
        final ImageButton imageButton = (ImageButton) itemView.findViewById(R.id.swip_image_view);
        TextView textView = (TextView) itemView.findViewById(R.id.imageCount);
        TextView textViewnmc = (TextView) itemView.findViewById(R.id.textViewNamaCalon);
        if(ingkod==""){
            ingkod = textViewnmc.getText().toString().trim();
        }
        container.addView(itemView);
        textView.setText("Calon : " + (position + 1));
        String imgurl = "https://voteapp-e3557.firebaseio.com/vote/"+ ingkod +"/pilihan/"+position+"/foto";
        vp.newListenTo(imgurl);
        vp.newListenTo("https://voteapp-e3557.firebaseio.com/vote/"+ ingkod +"/pilihan/"+position+"/nama");
        vp.startListenerToImageButton(imgurl,ctx,nowpos,imageButton);
        vp.startListenerToTextView(1,textViewnmc);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), KetVoteActivity.class);
                intent.putExtra(POS,String.valueOf(position));
                view.getContext().startActivity(intent);

            }
        });

        return itemView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }


}



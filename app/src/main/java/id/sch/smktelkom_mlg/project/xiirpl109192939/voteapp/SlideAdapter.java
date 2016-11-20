package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {

    public int[] imageResources =
            {R.drawable.calon1idxfg4d, R.drawable.calon2idxfg4d};
    String inviteCode;
    private Context ctx;
    private LayoutInflater layoutInflater;



    public SlideAdapter(Context c) {
        ctx = c;
    }

    public void setInviteCode(String inv){
        this.inviteCode = inv;
    }

    @Override
    public int getCount() {
        return imageResources.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.activity_slide, container, false);
        final ImageButton imageButton = (ImageButton) itemView.findViewById(R.id.swip_image_view);
        TextView textView = (TextView) itemView.findViewById(R.id.imageCount);
        imageButton.setImageResource(imageResources[position]);
        textView.setText("Calon : " + (position + 1));
        container.addView(itemView);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), KetVoteActivity.class);
                intent.putExtra("INVITE_CODE", inviteCode);
                intent.putExtra("POS", position + "");
                view.getContext().startActivity(intent);

//                Intent intent = new Intent(ctx, KetVoteActivity.class);
//                intent.putExtra(KETERANGAN,getImageResources());
//                ctx.startActivity(intent);
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



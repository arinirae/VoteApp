package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.HomeVote;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.LoginActivity;
import id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp.R;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView iv = (ImageView) findViewById(R.id.ivSplash);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an);
                finish();
                Intent intent = new Intent(splashActivity.this, LoginActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        Thread timerThread = new Thread(){
//            public void run(){
//                try{
//                    sleep(2000);
//                }catch(InterruptedException e){
//                    e.printStackTrace();
//                }finally{
//                    Intent intent = new Intent(splashActivity.this,HomeVote.class);
//                    startActivity(intent);
//                }
//            }
//        };
//        timerThread.start();
    }
//
//    @Override
//    protected void onPause() {
//        // TODO Auto-generated method stub
//        super.onPause();
//        finish();
//    }
    }


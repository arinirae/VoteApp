package id.sch.smktelkom_mlg.project.xiirpl109192939.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeVote extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int REQUEST_CODE_ADDVOTE = 144;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_vote);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //API
        VoteAPI ref = new VoteAPI();
        ref.init("https://voteapp-e3557.firebaseio.com/userInformation/" + (user.getUid()) + "/", this);
        ref.newListenTo("https://voteapp-e3557.firebaseio.com/userInformation/" + (user.getUid()) + "/name");
        ref.newListenTo("https://voteapp-e3557.firebaseio.com/userInformation/" + (user.getUid()) + "/email");
        //ref.newListenTo("gs://voteapp-e3557.appspot.com/Profile/profile_"+(user.getUid())+".jpg");
        //end API

        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        //membuat variable user untuk mengambil data

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        TextView tvNama = (TextView) hView.findViewById(R.id.textViewNama);
        TextView tvEmail = (TextView) hView.findViewById(R.id.textViewEmail);
        ImageView ivPP = (ImageView) hView.findViewById(R.id.imageViewPP);
        //tvNama.setText("Ini Nama");
        ref.startListenerToTextView(0, tvNama);
        ref.startListenerToTextView(1, tvEmail);
        ref.startListenerToImageView("gs://voteapp-e3557.appspot.com/Profile/profile_" + (user.getUid()) + ".jpg", this, 0, ivPP);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_vote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.nav_voting) {
            VoteFragment voteActivity = new VoteFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_home_vote, voteActivity, voteActivity.getTag()
            ).commit();

        } else if (id == R.id.nav_buatVoting) {
            Intent intbv = new Intent(getBaseContext(),AddVote.class);
            startActivityForResult(intbv, REQUEST_CODE_ADDVOTE);
        } else if (id == R.id.nav_riwayatVote) {
            RiwayatFragment riwayatVote = new RiwayatFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_home_vote, riwayatVote, riwayatVote.getTag()
            ).commit();

        } else if (id == R.id.nav_hasilVote) {
            HasilFragment hasilVote = new HasilFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_home_vote, hasilVote, hasilVote.getTag()
            ).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADDVOTE && resultCode == RESULT_OK) {
            Toast.makeText(HomeVote.this, "Vote Created.. Watch Your Vote!!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}

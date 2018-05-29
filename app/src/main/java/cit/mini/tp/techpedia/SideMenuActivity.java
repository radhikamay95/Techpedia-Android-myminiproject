package cit.mini.tp.techpedia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SideMenuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  /*  DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;*/

  Button MainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //main code
        showProgress();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        database = database.child(user.getUid());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hideProgress();
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                ((TextView) findViewById(R.id.tvusername)).setText("Hello " + userModel.getFirst_name() + " " + userModel.getLast_name()+" !!  ");
                ((TextView) findViewById(R.id.tvusernamehead)).setText(userModel.getFirst_name() + " " + userModel.getLast_name());

           /*tvusernamehead*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgress();
                Log.d("==>", "==>" + databaseError);

            }
        });
     /*   MainMenu = (Button) findViewById(R.id.bt_menu);
       *//* MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SideMenuActivity.this,
                        SideMenuActivity.class);
                startActivity(myIntent);*//*

            }
        });
*/

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
        getMenuInflater().inflate(R.menu.side_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

        @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();


        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/
            int id=item.getItemId();
            switch (id){

                case R.id.nav_Home:
                    Intent r= new Intent(SideMenuActivity.this,SideMenuActivity.class);
                    startActivity(r);
                    break;
                case R.id.nav_Blog:
                    Intent s= new Intent(SideMenuActivity.this,BlogView.class);
                    startActivity(s);
                    break;
                case R.id.nav_Books:
                    Intent w= new Intent(SideMenuActivity.this,BookListActivity.class);
                    startActivity(w);
                    break;
               case R.id.nav_profile:
                    Intent g= new Intent(SideMenuActivity.this,Student_ProfileActivity.class);
                    startActivity(g);
                    break;
                case R.id.nav_File:
                    Intent k= new Intent(SideMenuActivity.this,File_VIewActivity.class);
                    startActivity(k);
                /*case R.id.nav_tools:
                    Intent t= new Intent(SideMenuActivity.this,Tools.class);
                    startActivity(t);
                    break;*/
                // this is done, now let us go and intialise the home page.
                // after this lets start copying the above.
                // FOLLOW MEEEEE>>>
            }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

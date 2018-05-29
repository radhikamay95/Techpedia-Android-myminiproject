package cit.mini.tp.techpedia;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class BookDetailedViewActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView dName, dOwner, dAuthor,dprice,dcontact,daccessories,dedition,dremark,ddate;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        dName = (TextView) findViewById(R.id.tvdname);
        dAuthor = (TextView) findViewById(R.id.tvdauthor);
        dOwner = (TextView) findViewById(R.id.tvdprice);
        dprice = (TextView) findViewById(R.id.tvdowner);
        daccessories = (TextView) findViewById(R.id.tvdacc);
        dedition = (TextView) findViewById(R.id.tvdedit);
        dremark = (TextView) findViewById(R.id.tvdremark);
        dcontact = (TextView) findViewById(R.id.tvdcontact);
        ddate = (TextView) findViewById(R.id.tvddate);

        //GET INTENT
        Intent i = this.getIntent();


        String name = i.getExtras().getString("BookName");
        dName.setText("BOOK: "+name);
        String author = i.getExtras().getString("BookAuthor");
        dAuthor.setText("AUTHOR: "+author);
        String owner = i.getExtras().getString("BookOwner");
        dOwner.setText("OWNER: "+owner);
        String edition = i.getExtras().getString("BookEdition");
        dedition.setText("EDITION/PUBLISHER: "+edition);
        String price = i.getExtras().getString("BookPrice");
        dprice.setText("PRICE: "+price);
        String accc = i.getExtras().getString("Bookaccc");
        daccessories.setText("ACCESSORIES: "+accc);
        String contact = i.getExtras().getString("Bookcontact");
        dcontact.setText("CONTACT: "+contact);
        String remark = i.getExtras().getString("Bookremark");
        dremark.setText("REMARK: "+remark);
        String date = i.getExtras().getString("BookDate");
        ddate.setText("DATE: "+date);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("BookName"));

        }

    }
}

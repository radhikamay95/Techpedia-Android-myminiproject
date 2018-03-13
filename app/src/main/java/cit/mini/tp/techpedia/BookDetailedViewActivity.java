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
    TextView dName, dOwner, dAuthor,dprice,dcontact,daccessories,dedition,dremark;

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

        //GET INTENT
        Intent i = this.getIntent();


        String name = i.getExtras().getString("BookName");
        dName.setText(name);
        String author = i.getExtras().getString("BookAuthor");
        dAuthor.setText(author);
        String owner = i.getExtras().getString("BookOwner");
        dOwner.setText(owner);
        String edition = i.getExtras().getString("BookEdition");
        dedition.setText(edition);
        String price = i.getExtras().getString("BookPrice");
        dprice.setText(price);
        String accc = i.getExtras().getString("Bookaccc");
        daccessories.setText(accc);
        String contact = i.getExtras().getString("Bookcontact");
        dcontact.setText(contact);
        String remark = i.getExtras().getString("Bookremark");
        dremark.setText(remark);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("BookName"));

        }

    }
}

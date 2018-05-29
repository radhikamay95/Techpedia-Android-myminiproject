package cit.mini.tp.techpedia;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public  class Student_ProfileActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {



    TextView firstname,lastname,department,year,email,mobileno,clgname,sid ;


    //the hero list where we will store all the hero objects after parsing json
    ArrayList<UserModel> profile;




    //recycler
    DatabaseReference databaseBook = FirebaseDatabase.getInstance().getReference("Users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_student__profile);
        setContentView(R.layout.activity_student__profile);

        firstname = (TextView) findViewById(R.id.tvfirstname);
        lastname = (TextView) findViewById(R.id.tvlastname);
        email = (TextView) findViewById(R.id.tveditmail);
        department = (TextView) findViewById(R.id.tveditdepartment);
        mobileno = (TextView) findViewById(R.id.tveditphoneno);
        year = (TextView) findViewById(R.id.tvedityear);
        clgname = (TextView) findViewById(R.id.tveditclgname);
        sid = (TextView) findViewById(R.id.tveditstuid);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);

                // hideProgress()
                ((TextView) findViewById(R.id.tvfirstname)).setText("FIRSTNAME: " + userModel.getFirst_name());
                ((TextView) findViewById(R.id.tvlastname)).setText("LASTNAME: " + userModel.getLast_name());
                ((TextView) findViewById(R.id.tveditstuid)).setText("ID: " + userModel.getStudent_id());
                ((TextView) findViewById(R.id.tvedityear)).setText("YEAR: " + userModel.getStudy_year());
                ((TextView) findViewById(R.id.tveditdepartment)).setText("DEPARTMENT: " + userModel.getDepartment_name());
                ((TextView) findViewById(R.id.tveditmail)).setText("EMAILID: " + userModel.getEmail());
                ((TextView) findViewById(R.id.tveditphoneno)).setText("CONTACT: " + userModel.getMobile_number());
                ((TextView) findViewById(R.id.tveditclgname)).setText("COLLEGE: " + userModel.getCollege_name());




           /*tvusernamehead*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //hideProgress();
                Log.d("==>", "==>" + databaseError);

            }
        });
    }

        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            return false;
        }


    }


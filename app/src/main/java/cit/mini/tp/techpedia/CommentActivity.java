package cit.mini.tp.techpedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommentActivity extends AppCompatActivity {
    //comment fetch and parse the comment in listview
    private static final String JSON_URL = "https://techpediaapp-1cab0.firebaseio.com/Comments.json";
    ListView comment_list;
    ArrayList<Comment> commentlist=new ArrayList<>();

    EditText commentet;
    Button okay;
    private CommnetAdapter commnetAdapter;

    private FirebaseAuth mAuth;
    DatabaseReference databaseBook = FirebaseDatabase.getInstance().getReference("Comments");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        comment_list=(ListView)findViewById(R.id.listView2);
        commentet=(EditText)findViewById(R.id.ettyping);
        okay    =(Button)findViewById(R.id.btokay);


        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcomment();
                listcomment();
            }
        });


    }

    public long getItemId(int i) {
        return i;
    }


    private void addcomment() {
        String result = commentet.getText().toString().trim();
        if (!TextUtils.isEmpty(result)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseBook.push().getKey();

            //creating an Artist Object
            Comment comment = new Comment(result);

            //Saving the Artist
            databaseBook.child(id).setValue(comment);

            //setting edittext to blank again
            commentet.setText("");
            Toast.makeText(this, "Comment added", Toast.LENGTH_LONG).show();

        }

    }
    private void listcomment() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            // JSONArray bookArray = obj.getJSONArray("Value");

                            //now looping through all the elements of the json array
                            for (Iterator<String> it = obj.keys(); it.hasNext(); ) {
                                Object key = it.next();
                                //getting the json object of the particular index inside the array
                                String keyStr = (String)key;
                                JSONObject commentObject = obj.getJSONObject(keyStr);


                                //creating a hero object and giving them the values from json object
                                Comment comment = new Comment(commentObject.getString("comments_typied"));

                                //adding the hero to herolist
                                commentlist.add(comment);
                            }

                            //creating custom adapter object
                            commnetAdapter = new CommnetAdapter(CommentActivity.this, commentlist);

                            //adding the adapter to listview
                            comment_list.setAdapter(commnetAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}

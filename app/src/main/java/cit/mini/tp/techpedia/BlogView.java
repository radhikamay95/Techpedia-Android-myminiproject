package cit.mini.tp.techpedia;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.app.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class BlogView extends AppCompatActivity /* implements View.OnClickListener*/{
    //recyclerview object
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    //database reference
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseref_comment;

    //progress dialog
    private ProgressDialog progressDialog;

    //list to hold all the uploaded images
    private List<PostBaseclass> posts;
    private Button download;
    StorageReference storageReference;
/*
    FloatingActionButton filedownload;
    ProgressDialog pd;*/

    //commment
    /*DatabaseReference databaseReference;*/
    EditText com_typing;
    TextView com_sent;

    FloatingActionButton send,like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_view);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
      // Set the layout manager to your recyclerview
        recyclerView.setLayoutManager(mLayoutManager);

       /* filedownload=(FloatingActionButton)findViewById(R.id.fab_filedownload);
*/
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);

        posts = new ArrayList<>();

      /* //download=(Button) findViewById(R.id.fab_filedownload);
        //comment
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.Database_comment);
     *//*   com_typing=(EditText)findViewById(R.id.et_comment) ;
        com_sent=(TextView)findViewById(R.id.tvcommmentsent);
*//*
*/

        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference(PostActivity.Constants.Database_upload);//adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();
                System.out.println( snapshot.getChildrenCount());
                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    final PostBaseclass upload = postSnapshot.getValue(PostBaseclass.class);
                    upload.setBlogId(postSnapshot.getKey());
                    System.out.println(mDatabase.child(postSnapshot.getKey()).child("Comments"));
/*
                    mDatabase = mDatabase.child(postSnapshot.getKey()).child("Comments");
*/
                   /* mDatabaseref_comment.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long size = dataSnapshot.getChildrenCount();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
*/

//                    String JSON_URL = "https://techpediaapp-1cab0.firebaseio.com/Blog/"+postSnapshot.getKey()+"/Comments.json";
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//                                    //hiding the progressbar after completion
//
//
//                                    try {
//                                        //getting the whole json object from the response
//                                        JSONObject obj = new JSONObject(response);
//                                        int count=0;
//                                        for (Iterator<String> it = obj.keys(); it.hasNext(); ) {
//                                         count=count+1;
//                                        }
//
//                                        upload.setCommentCounts(String.valueOf(count));
//
//
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    //displaying the error in toast if occurrs
//                                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
                    posts.add(upload);
                }
                //creating adapter
                adapter = new MyAdapterBlog(getApplicationContext(), posts);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

//       TextView comment=(TextView)findViewById(R.id.tvcomment);
//       comment.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               //Intent intent = new Intent(this, CommentActivity.class);
//               //startActivity(intent);
//           }
//       });

    }

    /*public void storecomments() {

        PostBaseclass postBaseclass = new PostBaseclass(com_type.getText().toString());

        String commenttext = mDatabase.push().getKey();
        mDatabase.child(commenttext).setValue(postBaseclass);
    }*/

      public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.

          MenuInflater inflater = getMenuInflater();
          inflater.inflate(R.menu.menu_main_blog, menu);
          //   inflater.inflate(R.menu.menu_main_book, menu);

                 return true;
      }
      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
          int id = item.getItemId();
          if (id == android.R.id.home) {

              NavUtils.navigateUpFromSameTask(this);
              return true;
          }

          else if (id == R.id.action_addPost) {
              startActivity(new Intent(BlogView.this,PostActivity.class));
          }
          return super.onOptionsItemSelected(item);


      }/*
    public void downloadFile(){


        storageReference = FirebaseStorage.getInstance().getReference(PostActivity.Constants.Storagefile_upload);
        StorageReference sRef = storageReference.child("1519559906735.docx");
        // Reference to an image file in Firebase Storage
        sRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Use the bytes to display the image
                System.out.println
                        (bytes);
                String path= Environment.getExternalStorageDirectory()+"/"+"1519559906735.docx";
                try {
                    FileOutputStream fos=new FileOutputStream(path);
                    fos.write(bytes);
                    fos.close();
                    //Toast.makeText(BlogView.this, "Success!!!", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                     Toast.makeText(BlogView.this, e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(BlogView.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                pd.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    @Override
    public void onClick(View view) {
            pd=new ProgressDialog(this);
            pd.setProgress(100);;
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(false);
            pd.show();
//        if(view == filedownload){
//            pd=new ProgressDialog(this);
//            pd.setProgress(100);;
//            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            pd.setCancelable(false);
//            pd.show();
//            downloadFile();
//        }

    }*/
   /* public class Constants{
        public static final String Database_comment="Comments";

    }*/
}

package cit.mini.tp.techpedia;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class BookListActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener  {

    //the URL having the json data
    private static final String JSON_URL = "https://techpediaapp-1cab0.firebaseio.com/SellBooks.json";
    //listview object
    ListView listView;

    //the hero list where we will store all the hero objects after parsing json
    ArrayList<SellBook> booklist;

    //recycler
    DatabaseReference databaseBook = FirebaseDatabase.getInstance().getReference("SellBooks");
    private SearchView searchView;
    private MenuItem searchMenuItem;
    private BookListItemActivity bookAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);
        //initializing listview and hero list
        listView = (ListView) findViewById(R.id.listView);
        booklist = new ArrayList<SellBook>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        //this method will fetch and parse the data
        loadBookList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(BookListActivity.this, BookDetailedViewActivity.class);
                if(bookAdapter.getFilteredItem().size()>0){
                    intent.putExtra("BookName",bookAdapter.getFilteredItem().get(pos).getName());
                    intent.putExtra("BookAuthor",bookAdapter.getFilteredItem().get(pos).getAuthor());
                    intent.putExtra("BookOwner",bookAdapter.getFilteredItem().get(pos).getOwner());
                }else {
                    intent.putExtra("BookName", booklist.get(pos).getName());
                    intent.putExtra("BookAuthor", booklist.get(pos).getAuthor());
                    intent.putExtra("BookOwner", booklist.get(pos).getOwner());
                }
                startActivity(intent);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_book, menu);
        //   inflater.inflate(R.menu.menu_main_book, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        else if (id == R.id.action_search)
        {
            //startActivity(new Intent(BookListActivity.this,AddBookActivity.class));
            Toast.makeText(BookListActivity.this, "Started Search", Toast.LENGTH_LONG).show();
            Query firebaseSearchQuery = databaseBook.orderByChild("name");

        }
        else if (id == R.id.action_addBook) {
            startActivity(new Intent(BookListActivity.this,AddBookActivity.class));
        }
        return super.onOptionsItemSelected(item);


    }

       private void loadBookList() {
        //getting the progressbar
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
                                JSONObject bookObject = obj.getJSONObject(keyStr);


                                //creating a hero object and giving them the values from json object
                                SellBook books = new SellBook(bookObject.getString("id"), bookObject.getString("name"),bookObject.getString("owner"),bookObject.getString("author"));

                                //adding the hero to herolist
                                booklist.add(books);
                            }

                            //creating custom adapter object
                            bookAdapter = new BookListItemActivity(BookListActivity.this, booklist);


                            //adding the adapter to listview
                            listView.setAdapter(bookAdapter);

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // this.overridePendingTransition(R.anim.stay_in, R.anim.bottom_out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        bookAdapter.getFilter().filter(newText);

        // use to enable search view popup text
//        if (TextUtils.isEmpty(newText)) {
//            friendListView.clearTextFilter();
//        }
//        else {
//            friendListView.setFilterText(newText.toString());
//        }

        return true;
    }
}


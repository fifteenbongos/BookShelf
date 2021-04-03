package edu.temple.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class BookSearchActivity extends AppCompatActivity {

    // Activity triggers a search function
    // EditText allows user to enter a search term
    // Button wll perform the search
    // Optional button to cancel search and close activity
    // Results of search should be returned to the main activity (Intent)
    // When the user performs a search, the application must always display the BookListFragment

    EditText searchBox;
    Button cancelButton, searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        searchBox = findViewById(R.id.search_box);
        cancelButton = findViewById(R.id.cancel_button);
        searchButton = findViewById(R.id.accept_button);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("MyTag", "Button Clicked");
                Log.d("MyTag", searchBox.getText().toString());
                callAPI(searchBox.getText().toString());
            }
        });

    }

    private JSONArray callAPI(String args){
        Log.d("MyTag", "Entered callAPI()");


        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://kamorris.com/lab/cis3515/search.php?term=" + args;

        // Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                // On success, turn String to JSON object
                Log.d("MyTag", response);
                try{
                    JSONArray books = new JSONArray(response);
                    // TODO Pass books to the outer method
                } catch (Exception e) {
                    // Log error
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                // Error message

            }
        });

        queue.add(stringRequest);
        // Error
        return null;
    }
}
package com.gem.gemgridimagesearch;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.util.Log;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

import com.apptimize.Apptimize; 
import com.apptimize.ApptimizeExperiment;


public class MainActivity extends Activity {

	EditText etQuery;   // declare viesws (from layout graphical/xml)
	GridView gvResults;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>(); // array list for storing query based on our new class
	ImageResultArrayAdapter imageAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		Apptimize.setup(this, "CetwAOZf_15AjmmTSN9gAdTyTBoldzw"); // YOUR APP KEY
		
		setupViews();   // setup views by using the handle findViewById
		imageAdapter = new ImageResultArrayAdapter(this,  imageResults);
		gvResults.setAdapter(imageAdapter);
		
		//setup handler that exists in each view so when a thumbnail is clicked click puts up big view.
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View Parent, int position, long rowId) {
				 //intent is request to bring up new box. Context for start.
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				//use bundle to pass into request that can be used on both sides
				i.putExtra("url", imageResult.getFullUrl());  //pass key and value
				startActivity(i);  //fires the new view activity
				
			}
			
		} );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}
	
	public void onSearchClick (View v) {
		String query = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
	    
		// use async http client - access internet gets/puts, then manage response with handle
		// in browser can see JSON response:: https://ajax.googleapis.com/ajax/services/search/images?v=searchTerm
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + "start=" + 0
				+ "&v=1.0&q=" + Uri.encode(query), new JsonHttpResponseHandler() {
			// rsz is the size, start is when paginate, pass the 1.0 version, and pass the query as what is sent.
			// ie. http://ajax.googleapis.com/ajax/services/search/images?rsz=8&start=0&v=1.0&q=squid
			// need a handler - new JsonHttpResponseHandler()
		
			@Override
			public void onSuccess (JSONObject response) {
				// need to get the giant dictionary, and then get to the array of what we want, and get resutls.
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear();  // clears array that was define way before OnCreate (start fresh)
					//add items, use model to parse results
					imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
					//notify adapter = imageAdapter.notify() or call directly - change imageResults to imageAdapter above.
				
					
					Log.d("DEBUG", "imageResults " + imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}		
			}
		});
	}
	// query api, parsing the json, and at least print json to logcat.	
}
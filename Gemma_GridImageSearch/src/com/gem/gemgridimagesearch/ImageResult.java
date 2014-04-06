package com.gem.gemgridimagesearch;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//  get JSON field from google image search api and use object in project
// describe fields in object, and how to parse JSON into object
// in browser can see JSON response:: https://ajax.googleapis.com/ajax/services/search/images?v=searchTerm


public class ImageResult {
	
	// define our fields
	private String fullUrl;    // full Url
	private String thumbUrl;   //tbUrl

	//constructor for a model, ie. accepting a JSON object
	// pass in a dictionary of keys (url, tbUrl)
	// later could add title, size, other keys...
	public ImageResult(JSONObject json) {
		try {
			this.fullUrl = json.getString("url");  //url+tburl comes from category in JSON returned from google image search
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
	}
	
	// now make getters for the 2 fields
	public String getFullUrl () {
		return fullUrl;
	}
	
	public String getThumbUrl () {
		return thumbUrl;
	}
	
	public String toString() {
		return this.thumbUrl;
	}

	//static so can access directly from image results
	// process array, iterate and aggregate image results objects.
	public static ArrayList<ImageResult> fromJSONArray(JSONArray array) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int x = 0; x < array.length(); x++) {
			try {
				results.add(new ImageResult(array.getJSONObject(x)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
}


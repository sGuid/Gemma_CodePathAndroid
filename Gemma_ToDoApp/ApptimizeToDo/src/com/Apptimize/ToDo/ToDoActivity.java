package com.Apptimize.ToDo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

import com.apptimize.Apptimize;
import com.apptimize.ApptimizeExperiment;


public class ToDoActivity extends Activity {
	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		
//		 Apptimize.setup(this, "XXXxxXXxxxXXXxxXXxxxXXXxxXXxxxX");    // Copy/Paste Your App Key Code Snippet Here !	
		
		//Sets up ListView with items
		lvItems = (ListView) findViewById(R.id.lvItems);
		items = new ArrayList<String>();
		itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);
		
		setupListViewListener();

		items.add("First Item");
		items.add("BaseLine");
		
//		Apptimize.runExperiment("new android a/b test", new ApptimizeExperiment() {			
//		    @Override
//		    public void baseline() {
//		       // Variant: baseline
//		    	items.add("First Item");
//				items.add("BaseLine");
////				Apptimize.goalAchieved("button click");
//				Log.i("gg","**baseline**");
//		    }
//
//		    @SuppressWarnings("unused")
//		    public void variation1() {
//		       // Variant: var 1
//		    	items.add("First Item");
//				items.add("Variant 1");
//				Apptimize.goalAchieved("FACEBOOK");
//				Apptimize.goalAchieved("button click");
//				Log.i("gg","**var 1** ");
//		    }
//		});
		
		
			
	}

	public void addTodoItem(View v){
		// On Button (btnAddItem) click, adds text (edNewItem) to ListView (lvItems)
		EditText edNewItem = (EditText) findViewById(R.id.edNewItem);
		itemsAdapter.add(edNewItem.getText().toString());;
		edNewItem.setText("");	
//		Apptimize.goalAchieved("button click");
	}
	
	private void setupListViewListener(){
		// Removes an item from the listView list when item is held - long click
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> aView, View item, int pos, long id) {
				items.remove(pos);
				itemsAdapter.notifyDataSetInvalidated();
				return true;
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do, menu);
		return true;
	}

}
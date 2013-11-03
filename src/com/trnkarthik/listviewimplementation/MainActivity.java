package com.trnkarthik.listviewimplementation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView main_activity_list_view = null;
	ArrayList<String> myMainList = new ArrayList<String>();
	ArrayAdapter<String> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		for(int i=0;i<10;i++){
		//adding contents to ArrayList
		myMainList.add("Simple ListView");
		myMainList.add("Custom Array Adapter");
		myMainList.add("Simple Cursor Adapter");
		myMainList.add("Custom Cursor Adapter");
		}
		
		//Fetching the ListView
		main_activity_list_view =  (ListView)findViewById(R.id.main_activity_list_view);
        
		//defining a simple adapter
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, myMainList);

		//attaching the adapter to the listview
		main_activity_list_view.setAdapter(adapter);
		//onItemClickListner on listview
		main_activity_list_view.setOnItemClickListener(new OnItemClickListener() {

			Intent intent = null;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				//show the count in the listview
				Toast.makeText(getApplicationContext(), "Total count : " + main_activity_list_view.getCount() +"\n Total child count :  " + main_activity_list_view.getChildCount(), Toast.LENGTH_LONG).show();

				switch(arg2){
				case 0 :	
					intent = new Intent(MainActivity.this, SimpleListViewActivity.class);
					break;
				case 1 :
					intent = new Intent(MainActivity.this, CustomArrayListViewActivity.class);
					break;
				case 2 :
					intent = new Intent(MainActivity.this, SimpleListViewActivity.class);
					break;
				case 3 :
					intent = new Intent(MainActivity.this, SimpleListViewActivity.class);
					break;
				case 4 :
					intent = new Intent(MainActivity.this, SimpleListViewActivity.class);
					break;
				}

				startActivity(intent);

				//adding and removing items from listview
				//adapter.add("99");

			}
		});

		main_activity_list_view.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//removing item on long press
				adapter.remove(myMainList.get(arg2));

				return false;
			}
		});


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}

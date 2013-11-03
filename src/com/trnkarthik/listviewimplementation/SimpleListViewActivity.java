package com.trnkarthik.listviewimplementation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleListViewActivity extends Activity {

	ListView mySimpleLV = null;
	ArrayAdapter<String> adapter = null;
	ArrayList<String> resource = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_list_view);
		
		resource.add("Item 1");
		resource.add("Item 2");
		resource.add("Item 3");
		resource.add("Item 4");
		resource.add("Item 5");
		
		mySimpleLV = (ListView)findViewById(R.id.simple_list_view);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1, resource);
		
		mySimpleLV.setAdapter(adapter);
		
		//onclick show item details
		mySimpleLV.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), mySimpleLV.getItemAtPosition(arg2).toString() + " at position " + arg2 + " + 1", Toast.LENGTH_LONG).show();
			}
		});
		
		//on long click,item will be removed.
		mySimpleLV.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Toast.makeText(getApplicationContext(), mySimpleLV.getItemAtPosition(arg2).toString() + " removed " , Toast.LENGTH_LONG).show();
				adapter.remove(mySimpleLV.getItemAtPosition(arg2).toString());
				return false;
			}		
		});
				
 	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.simple_list_view, menu);
		return true;
	}

}

package com.trnkarthik.listviewimplementation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
        
        for(int i=0; i<10;i++)
        myMainList.add(i + " ");
         
        main_activity_list_view =  (ListView)findViewById(R.id.main_activity_list_view);
        
        adapter = new ArrayAdapter<String>(this, 
        	 	 android.R.layout.simple_list_item_1, android.R.id.text1, myMainList);
        main_activity_list_view.setAdapter(adapter);
        
        main_activity_list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
 				Toast.makeText(getApplicationContext(), arg2 +"", Toast.LENGTH_LONG).show();
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

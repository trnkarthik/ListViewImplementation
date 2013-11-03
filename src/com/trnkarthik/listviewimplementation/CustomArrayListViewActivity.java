package com.trnkarthik.listviewimplementation;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") public class CustomArrayListViewActivity extends Activity {

	ArrayList<CustomObject> myResourceList = null;
	CustomArrayAdapter myAdapter = null;
	ListView myListView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//reusing the main layout file
		setContentView(R.layout.activity_main);

		myListView = (ListView)findViewById(R.id.main_activity_list_view);


		//create custom objects 
		CustomObject obj1 = new CustomObject("obj1",getResources().getDrawable(R.drawable.sample_image));
		CustomObject obj2 = new CustomObject("obj2",getResources().getDrawable(R.drawable.ic_launcher));
		CustomObject obj3 = new CustomObject("obj3",getResources().getDrawable(R.drawable.sample_image));
		CustomObject obj4 = new CustomObject("obj4",getResources().getDrawable(R.drawable.ic_launcher));
		CustomObject obj5 = new CustomObject("obj5",getResources().getDrawable(R.drawable.sample_image));
		CustomObject obj6 = new CustomObject("obj6",getResources().getDrawable(R.drawable.ic_launcher));
		CustomObject obj7 = new CustomObject("obj7",getResources().getDrawable(R.drawable.sample_image));
		CustomObject obj8 = new CustomObject("obj8",getResources().getDrawable(R.drawable.ic_launcher));
		CustomObject obj9 = new CustomObject("obj9",getResources().getDrawable(R.drawable.sample_image));
		CustomObject obj10 = new CustomObject("obj10",getResources().getDrawable(R.drawable.ic_launcher));

		//declaring the listview
		myResourceList = new ArrayList<CustomObject>();

		//populating the listview
		for(int i=0;i<10;i++){
			myResourceList.add(obj1);
			myResourceList.add(obj2);
			myResourceList.add(obj3);
			myResourceList.add(obj4);
			myResourceList.add(obj5);
			myResourceList.add(obj6);
			myResourceList.add(obj7);
			myResourceList.add(obj8);
			myResourceList.add(obj9);
			myResourceList.add(obj10);
		}

		//onlongclick listner
		myListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Toast.makeText(CustomArrayListViewActivity.this, "Long pressed ", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		//custom adapter
		myAdapter = new CustomArrayAdapter(this, android.R.layout.simple_list_item_1, myResourceList);

		myListView.setAdapter(myAdapter);

		//multi items select 
		myListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		myListView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

		    @Override
		    public void onItemCheckedStateChanged(ActionMode mode, int position,
		                                          long id, boolean checked) {
		        // Here you can do something when items are selected/de-selected,
		        // such as update the title in the CAB
		    	
		    	int wantedPosition = position; // Whatever position you're looking for
		    	int firstPosition = myListView.getFirstVisiblePosition() - myListView.getHeaderViewsCount(); // This is the same as child #0
		    	int wantedChild = wantedPosition - firstPosition;
		    	// Say, first visible position is 8, you want position 10, wantedChild will now be 2
		    	// So that means your view is child #2 in the ViewGroup:
		    	if (wantedChild < 0 || wantedChild >= myListView.getChildCount()) {
 		    	  return;
		    	}
		    	// Could also check if wantedPosition is between listView.getFirstVisiblePosition() and listView.getLastVisiblePosition() instead.
		    	View v = myListView.getChildAt(wantedChild);

		    	v.setBackgroundColor(Color.GREEN);
		    	
				Toast.makeText(CustomArrayListViewActivity.this, position + " ", Toast.LENGTH_SHORT).show();
		    }

		    @Override
		    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		        // Respond to clicks on the actions in the CAB
		        switch (item.getItemId()) {
		            case R.id.add_new_item:
		                myAdapter.add(new CustomObject("karthik", getResources().getDrawable(R.drawable.sample_image)));
		                mode.finish(); // Action picked, so close the CAB
		                return true;
		            default:
		                return false;
		        }
		    }

		    @Override
		    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		        // Inflate the menu for the CAB
		        MenuInflater inflater = mode.getMenuInflater();
		        inflater.inflate(R.menu.custom_long_press, menu);
		        return true;
		    }

		    @Override
		    public void onDestroyActionMode(ActionMode mode) {
		        // Here you can make any necessary updates to the activity when
		        // the CAB is removed. By default, selected items are deselected/unchecked.
		    }

		    @Override
		    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		        // Here you can perform updates to the CAB due to
		        // an invalidate() request
		        return false;
		    }
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_array_list_view, menu);
		return true;
	}

	//custom adapter
	public class CustomArrayAdapter extends ArrayAdapter<CustomObject>{

		Context myContext = null;
		ArrayList<CustomObject> myresList = new ArrayList<CustomObject>();

		public CustomArrayAdapter(Context context, int resource,
				List<CustomObject> objects) {
			super(context, resource, objects);

			this.myresList = (ArrayList<CustomObject>) objects; 
			this.myContext = context;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = null;
 
			CustomObject currentObj = myresList.get(position);
			
			//convertview will be non null when ListView is asking you recycle the row layout.
			if(convertView == null){
				inflater = (LayoutInflater) myContext.getSystemService(myContext.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.custom_list_item, parent, false);	
				Toast.makeText(getApplicationContext(), "null(creating this view for the first time) for : " + position, Toast.LENGTH_SHORT).show();
			}

			ImageView myImage = (ImageView) convertView.findViewById(R.id.custom_lis_item_image);
			TextView myText = (TextView) convertView.findViewById(R.id.custom_lis_item_text);

			myImage.setImageDrawable(currentObj.getResourceid());

			myText.setText(currentObj.getName());

			return convertView;
		}


	}

	
	
	
	
	
	public class MyMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Select Items");
            mode.setSubtitle("One item selected");
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return true;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
                boolean checked) {
            int selectCount = myListView.getCheckedItemCount();
            switch (selectCount) {
            case 1:
                mode.setSubtitle("One item selected");
                break;
            default:
                mode.setSubtitle("" + selectCount + " items selected");
                break;
            }
        }

    }
	
	
	
	
	
	
}

package net.vadchernov.serverview;

import java.util.Random;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ServerViewActivity extends ListActivity {

	
	
		public class MyCustomAdapter extends ArrayAdapter<String> {
			
			public MyCustomAdapter(Context context, int textViewResourceId,
			String[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			//return super.getView(position, convertView, parent);
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.row, parent, false);
			TextView label=(TextView)row.findViewById(R.id.text);
			label.setText(serverMetric[position]);
			ImageView icon=(ImageView)row.findViewById(R.id.icon);

			if (serverMetricStatus[position]==0){
				icon.setImageResource(R.drawable.green);
			}
			else if (serverMetricStatus[position]==1) {
				icon.setImageResource(R.drawable.yellow);
			}
			else if (serverMetricStatus[position]==2) {
				icon.setImageResource(R.drawable.red);
			}
			else {
				icon.setImageResource(R.drawable.grey);
			}
			return row;
		}
	}
		
		
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   //setContentView(R.layout.main);
		   /*setListAdapter(new ArrayAdapter<String>(this,
		     R.layout.row, R.id.weekofday, DayOfWeek));*/
	       Bundle extras = getIntent().getExtras();
	       String id = extras.getString("id");
	        
		   int num = 15;
		   Random rand = new Random();
		   for (int i=0; i<15; i++) {
			   int percent = rand.nextInt(100);
			   int status = rand.nextInt(3);
			   serverMetricStatus[i]=status;
			   serverMetric[i] += percent + "%";
		   }
		   //serverMetricStatus
			View header = getLayoutInflater().inflate(R.layout.header, null);
			View footer = getLayoutInflater().inflate(R.layout.footer, null);
			ListView listView = getListView();
			listView.addHeaderView(header);
			listView.addFooterView(footer);
			TextView headerTextView = (TextView)findViewById(R.id.header);
			headerTextView.setText("Server  " + id);
			TextView footerTextView = (TextView)findViewById(R.id.footer);
			footerTextView.setText("Server  " + id);
			
		   setListAdapter(new MyCustomAdapter(this, R.layout.row, serverMetric));
		}
		
		//menu
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main_menu, menu);
	        return true;
	    }
		
		@Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	        case R.id.exit:
	            finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
		
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);
		String selection = l.getItemAtPosition(position).toString();
//		Toast.makeText(this, selection, Toast.LENGTH_LONG).show();
	    
		Intent intent = new Intent();
	    intent.setClass(this, RunActivity.class);
	    intent.putExtra(RunActivity.EXT_ID, selection);
	    
	    startActivity(intent);
	   // finish();
		
		}	
		
	    public int[] serverMetricStatus = new int[] {
	        2, 
	        1, 
	        1,
	        3, 
	        3,
	        0, 
	        0, 
	        0,
	        2,
	        0,
	        0,
	        1,
	        0,
	        0,
	        0
	      };
     public String[] serverMetric = new String[] {
        "Cpu: ", 
        "Mem: ", 
        "Disk sda: ",
        "Disk sdb: ", 
        "Disk sdc: ",
        "Disk sdd: ", 
        "Temp Cpu: ", 
        "Temp Case: ",
        "Users: ",
        "Apache: ",
        "Mysql: ",
        "Java: ",
        "Nginx: ",
        "Nagios: ",
        "Other: %"
      };
}
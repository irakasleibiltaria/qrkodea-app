package com.lzz.barkodea10;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	List<String> datuak = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// listan aukeratzean inzidentzia sartzeko
		ListView listView = (ListView) findViewById(R.id.listView1);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
		      @Override
		      public void onItemClick(AdapterView<?> parent, View view,
		          int position, long id) {
		    	  

	        	  // selected item
	        	  String product = ((TextView) view).getText().toString();
	        	  
	        	  Intent i = new Intent(getApplicationContext(), inzidentziak.class);
	        	  // sending data to new activity
	        	  i.putExtra("product", product);
	        	  startActivity(i);
		      }
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void hasi(View view) {
		scanSomething();
		
	}
	
	public void bidali(View view) {
        /*
         * send data
         *
         */
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setType("text/html");
        intent2.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        //intent2.putExtra(Intent.ACTION_SENDTO, "proba@proba.com");
        intent2.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent2.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

        startActivity(Intent.createChooser(intent2, "Send Email"));
	}
	
	public void gorde(View view) {
		// do something
		

	}
	
	

	public void scanSomething() {
	    // I need things done!  Do I have any volunteers?
	    Intent intent = new Intent("com.google.zxing.client.android.SCAN");

	    // This flag clears the called app from the activity stack, so users arrive in the expected
	    // place next time this application is restarted.
	    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

	    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
	    //intent.putExtra("SAVE_HISTORY",true);
	    intent.putExtra("PROMPT_MESSAGE", "kaixo");
	    intent.putExtra("RESULT_DISPLAY_DURATION_MS", 1L);
	    
	    startActivityForResult(intent, 0);
	}
	
	
	/*
	 * for no lost data when change orientation: 
	 * http://developer.android.com/guide/components/fragments.html
	 */
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            //  The Intents Fairy has delivered us some data!
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            // Handle successful scan
	            
	            
	            ListView listView = (ListView) findViewById(R.id.listView1);
	            //String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	            //  "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	            //  "Linux", "OS/2" };
	            
	            
	            /*
	             * if the data exist, dont add (todo)
	             * and, save it in a external place (http://developer.android.com/training/basics/data-storage/shared-preferences.html)
	             */
	            
	                
	            datuak.add(contents);
	            
	            
	            String[] simpleArray = new String[ datuak.size() ];
	            datuak.toArray( simpleArray );

	            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, simpleArray);

	            // Assign adapter to ListView
	            listView.setAdapter(adapter);
	            
	            
	            
	            
	            // show content in a popup
	            Context context = getApplicationContext();
	            CharSequence text = contents;
	            int duration = Toast.LENGTH_SHORT;

	            Toast toast = Toast.makeText(context, text, duration);
	            toast.show();
	            
	                       
	            // berriro scan
	            scanSomething();
	            
	            
	            
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    }
	}
	
	
}

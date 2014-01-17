package com.test.test3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

public class DisplayMessageActivity extends Activity {
	
	String file_content;
	String line;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		// Show the Up button in the action bar.
		setupActionBar();
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	   //wWe can create the text view or select an existing textview
	    TextView textView = (TextView) findViewById(R.id.message_shown);
	    // Create the text view
	    //TextView textView = new TextView(this);
	    textView.setTextSize(20);
	    textView.setText("message: "+message+"\n");
	    
	    String saved_message = MainActivity.getDefaults("saved", this);
	   textView.append("Shared preferences:"+saved_message+"\n");
	   MainActivity.setDefaults("saved",saved_message+message , this);
	 
	   try {
       	FileInputStream input = this.openFileInput(MainActivity.filename);
       	InputStreamReader isr = new InputStreamReader(input);
       	BufferedReader buffer = new BufferedReader(isr);
       	StringBuilder sb = new StringBuilder();
       	file_content=buffer.readLine();
       	while ((line = buffer.readLine()) != null) {
            sb.append(line);
        }
       } catch (Exception e) {
     	  e.printStackTrace();
     	}
	   textView.append("\n File internal storage: "+line);
	    
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

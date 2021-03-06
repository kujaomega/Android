package com.test.test3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.test.myfirstapp.Message";
	public static String filename = "saved_message.txt";
	FileOutputStream outputStream;
	String file_content;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        File file = new File(this.getFilesDir(), filename);
        
        try {
        	  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        	  outputStream.write("Hello_world".getBytes());
        	  outputStream.close();
        	} catch (Exception e) {
        	  e.printStackTrace();
        	}
        
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * If you specify a method on the layout, the method must be:
     * public, void, View as the only parameter.
     * @param view
     */
    public void sendMessage(View view)
    {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById (R.id.edit_message);
    	String message = editText.getText().toString();
    	
    	setDefaults("saved", message, this);
    	
    	try {
    		  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
    		  outputStream.write(message.getBytes());
    		  outputStream.close();
    		} catch (Exception e) {
    		  e.printStackTrace();
    		}
    	
    	
    	
    	intent.putExtra(EXTRA_MESSAGE,  message);
    	startActivity(intent);
    }
    
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }
    
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
    
}

package com.example.translator;


import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayTranslationActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
		 //Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(TranslateActivity.EXTRA_MESSAGE);
	    
	    	String out = Translate.execute(message, Language.AUTO_DETECT, Language.FRENCH);
		    //String out = Translate.execute("hello", Language.ENGLISH, Language.FRENCH);
	    	
	    	// Create the text view
		    TextView textView = new TextView(this);
		    textView.setTextSize(40);
		    textView.setText("hello");

		    // Set the text view as the activity layout
		    setContentView(textView);	
	    }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

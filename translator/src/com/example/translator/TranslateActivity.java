package com.example.translator;

import java.util.*;
 
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
 


import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
 
public class TranslateActivity extends Activity implements OnInitListener {
 
    public static final String EXTRA_MESSAGE = null;
	private TextToSpeech tts;
	private String Lang = "ENGLISH";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
         
        //Speak Button
        tts = new TextToSpeech(this, this);
        ((Button) findViewById(R.id.bSpeak)).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	if (Lang.equals("GERMAN")) {
               	 tts.setLanguage(Locale.GERMAN);
               	
       	       }
       	       if (Lang.equals("ENGLISH")) {
       	    	   tts.setLanguage(Locale.ENGLISH);
       	       }
       	       if (Lang.equals("CHINESE")) {
       	    	   tts.setLanguage(Locale.CHINESE);
       	       }
       	       if (Lang.equals("JAPANESE")) {
       	    	  tts.setLanguage(Locale.JAPANESE);
       	       }
       	       if (Lang.equals("FRENCH")) {
       	    	  tts.setLanguage(Locale.FRENCH);
       	       }
       	       if (Lang.equals("KOREAN")) {
       	    	   tts.setLanguage(Locale.KOREAN);
       	       }
       	       if (Lang.equals("ITALIAN")) {
       	    	   tts.setLanguage(Locale.ITALIAN);
       	       }
       	    if (((TextView) findViewById(R.id.tvTranslatedText)).getText().toString().equals("")){
       	    	speakOut(((TextView) findViewById(R.id.txtMessage1)).getText().toString());
        }else{
        	speakOut(((TextView) findViewById(R.id.tvTranslatedText)).getText().toString());
        }
            }
        });
        
        //Translate Button
        ((Button) findViewById(R.id.bTranslate)).setOnClickListener(new OnClickListener() {

        	@Override
        	public void onClick(View v) {
        	  // TODO Auto-generated method stub
        	   
        	   
        	   
        	  class bgStuff extends AsyncTask<Void, Void, Void>{
        	       
        	      String translatedText = "";
        	      @Override
        	      protected Void doInBackground(Void... params) {
        	          // TODO Auto-generated method stub
        	          try {
        	              String text = ((TextView) findViewById(R.id.txtMessage1)).getText().toString();
        	              translatedText = translate(text,Lang);
        	          } catch (Exception e) {
        	              // TODO Auto-generated catch block
        	              e.printStackTrace();
        	              translatedText = e.toString();
        	          }
        	           
        	          return null;
        	      }

        	      @Override
        	      protected void onPostExecute(Void result) {
        	          // TODO Auto-generated method stub
        	          ((TextView) findViewById(R.id.tvTranslatedText)).setText(translatedText);
        	          super.onPostExecute(result);
        	      }
        	       
        	  }
        	   
        	  new bgStuff().execute();
        	}
        	});

        
      ///Language Spinners 
        Spinner spinner = (Spinner) findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ((Spinner) findViewById(R.id.language_spinner)).setOnItemSelectedListener(new OnItemSelectedListener() {
            
        	public void onItemSelected(AdapterView<?> parent, View view, 
                    int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                Lang= (String) parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
   }
 
  ///translator 
  	 public String translate(String text, String Lang) throws Exception{
  	       
  	        
  		    // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
  		       Translate.setClientId("MY1stApp"); //Change this
  		       Translate.setClientSecret("Wk/am4ZEpzgPWSl9vzewRmMnW5W9aqnrETblvH/K2Xw="); //change
  		        
  		        
  		       String translatedText = "";
  		       if (Lang.equals("GERMAN")) {
  		    	   translatedText = Translate.execute(text,Language.GERMAN);
  		       }
  		       if (Lang.equals("ENGLISH")) {
  		    	   translatedText = Translate.execute(text,Language.ENGLISH);
  		       }
  		       if (Lang.equals("CHINESE")) {
  		    	   translatedText = Translate.execute(text,Language.CHINESE_SIMPLIFIED);
  		       }
  		       if (Lang.equals("JAPANESE")) {
  		    	   translatedText = Translate.execute(text,Language.JAPANESE);
  		       }
  		       if (Lang.equals("FRENCH")) {
  		    	   translatedText = Translate.execute(text,Language.FRENCH);
  		       }
  		       if (Lang.equals("KOREAN")) {
  					translatedText = Translate.execute(text,Language.KOREAN);
  		       }
  		       if (Lang.equals("ITALIAN")) {
  		    	   translatedText = Translate.execute(text,Language.ITALIAN);
  		       }
  		       
  		       //translatedText = Translate.execute(text,Language.GERMAN);
  		        
  		       return translatedText;
  		   }
 
@Override
public void onInit(int status) {
    // TODO Auto-generated method stub
    if (status == TextToSpeech.SUCCESS) {
          
        int result = tts.setLanguage(Locale.GERMAN);
 
        if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.e("TTS", "This Language is not supported");
        } else {
             
            //speakOut("Ich");
        }
 
    } else {
        Log.e("TTS", "Initilization Failed!");
    }
}
    
private void speakOut(String text) {
    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
}
    
}
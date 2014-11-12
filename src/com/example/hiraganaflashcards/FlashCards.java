package com.example.hiraganaflashcards;


import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class FlashCards extends FragmentActivity
{
	
	HashMap <String, String> chosenItems;
	protected void onCreate(Bundle savedInstanceState) 
    {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.flash_card);
       
       chosenItems= new HashMap<String,String>();
       TextView tv = (TextView)findViewById(R.id.tvKatakana);
       String text="";
       chosenItems =  (HashMap<String, String>) getIntent().getExtras().get("selected");
      
       tv.setText(text);
       
    }
	
	public void parseSelectedItems(ArrayList<String> list)
	{
		String tokens [];
		  for (String each_item:list)
	       {
	    	   tokens=each_item.split(" ");
	    	   chosenItems.put(tokens[0], tokens[1]);
	       }
	}
	
	
}

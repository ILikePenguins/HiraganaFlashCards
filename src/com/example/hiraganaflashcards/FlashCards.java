package com.example.hiraganaflashcards;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FlashCards extends FragmentActivity
{
	
	HashMap <String, String> chosenItems;
	String answer;
	TextView tv;
	String randomValue;
	String randomKey;
	protected void onCreate(Bundle savedInstanceState) 
    {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.flash_card);
       
       chosenItems= new HashMap<String,String>();
       tv= (TextView)findViewById(R.id.tvKatakana);
       chosenItems =  (HashMap<String, String>) getIntent().getExtras().get("selected");
       
       Button buNext = (Button)findViewById(R.id.buNext);
       buNext.setOnClickListener(new NextButton());
       
       Button buShowAnswer = (Button)findViewById(R.id.buShowAnswer);
       buShowAnswer.setOnClickListener(new ShowAnswer());
       
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
	
	public class NextButton implements OnClickListener
	{

		public void onClick(View arg0) 
		{
			flashCard();
		}
		
	}
	
	
	public class ShowAnswer implements OnClickListener
	{

		public void onClick(View arg0) 
		{
				 tv.setText(randomValue + " is " +randomKey);
		}
		
	}
	
	
	public void flashCard()
	{
		Random generator = new Random();
		Object[] keys = chosenItems.keySet().toArray();
		randomKey = (String)keys[generator.nextInt(keys.length)];
		randomValue= chosenItems.get(randomKey);
		
		tv.setText(randomValue);
		 
		
	}
	
	
}

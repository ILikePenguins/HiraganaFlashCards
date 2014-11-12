package com.example.hiraganaflashcards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends ActionBarActivity
{
	HashMap<String, String> map;
	ArrayList<String> letters;
	GridView gridView;
	HashMap<Integer, Integer>positions;
	HashMap<String, String>chosenItems;
	//ArrayList<String> chosenItems;
	
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = new HashMap<String,String>();
        gridView = (GridView) findViewById(R.id.gridview);
        letters=new ArrayList<String>();
        //chosenItems=new ArrayList<String>();
        positions=new HashMap<Integer,Integer>();
        chosenItems= new HashMap<String, String>();
        read();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, letters);
        gridView.setAdapter(adapter);
        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gridView.setMultiChoiceModeListener(new MultiChoiceModeListener());
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new StartButton());
    }

    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) 
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 
    
public class MultiChoiceModeListener implements GridView.MultiChoiceModeListener 
{
	String item;
	String[] tokens;
	public boolean onCreateActionMode(ActionMode mode, Menu menu) 
	{
	    mode.setTitle("Select Items");
	    mode.setSubtitle("One item selected");
	    return true;
	}
	
	public boolean onPrepareActionMode(ActionMode mode, Menu menu)
	{
	    return true;
	}
	
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) 
	{
	    return true;
	}
	
	public void onDestroyActionMode(ActionMode mode)
	{
	}
	
	public void onItemCheckedStateChanged(ActionMode mode, int position,
	        long id, boolean checked) 
	{
	    int selectCount = gridView.getCheckedItemCount();
	    //add chosen item to hashmap
	    item=(String)(gridView.getItemAtPosition(position));
	    tokens = item.split(" ");
	    
	    if(chosenItems.containsKey(tokens[0]))
	    	chosenItems.remove(tokens[0]);
	    else
	    	chosenItems.put(tokens[0], tokens[1]);
	    
	    if(positions.containsKey(position))
	    	positions.remove(position);
	    else
	    	positions.put(position, position);
	    switch (selectCount) 
	    {
	    
	    case 1:
	        mode.setSubtitle("One item selected");
	        break;
	    default:
	        mode.setSubtitle("" + selectCount + " items selected");
	        break;
	    }
	}
	
}

public class StartButton implements OnClickListener
{

	public void onClick(View arg0)
	{
		   //start flash card view
        Intent flashCardIntent = new Intent(MainActivity.this,FlashCards.class);
        //loads map if it is not loaded already
        flashCardIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //pass values to the map activity
        flashCardIntent.putExtra("selected", chosenItems);
        startActivity(flashCardIntent);
	}
	
}


    public void read()
	{
    	//TextView tv = (TextView)findViewById(R.id.test);
    	
		
		try 
		{
			BufferedReader reader = new BufferedReader  (new InputStreamReader(getAssets().open("katakana.txt")));
			String line=" ";
			String[] tokens;
			while((line=reader.readLine() ) !=null)
			{
				//check for blank lines
				 line = line.trim();
				    if (line.length() == 1) 
				    {
				        continue;
				    }
				    letters.add(line);
				    
					tokens=line.split(" ");
					map.put(tokens[0]+" ", tokens[1]);
			}

//			 Iterator it =  map.entrySet().iterator();
//			 while(it.hasNext())
//             {
//				 Map.Entry me= (Map.Entry)it.next();
//				 System.out.println(me.getKey()+" " +me.getValue());
//             }
             
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

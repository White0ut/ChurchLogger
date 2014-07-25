package edu.wmich.cs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MessageListFrag extends ListFragment  
{  
	  String[] test = new String[] {"Bible study moved back an hour this week", "Palm Sunday server begins at 1:00PM", 
			  "Feel free to bring a friend or two this week for Palm Sunday!"};

	  ArrayAdapter<String> adapter;
	  
	  @Override  
	  public void onListItemClick(ListView l, View v, int position, long id) {  
	       
	  }  
	  
	  @Override  
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	    Bundle savedInstanceState) {  
		  adapter = new ArrayAdapter<String>(  
	     inflater.getContext(), android.R.layout.simple_list_item_1,  
	     test);  
	   setListAdapter(adapter);  
	   return super.onCreateView(inflater, container, savedInstanceState);  
	  }  
	  
	  
	  
	  
	 } 

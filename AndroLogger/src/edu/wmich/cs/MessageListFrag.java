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
	  
	  String[] numbers_text = new String[] { "Hear ye Hear ye, this is a test for the churches yo motha", 
	  "We need a fucking battery pack, my mic is dieing left and RIGHT!"};

	  ArrayAdapter<String> adapter;
	  
	  @Override  
	  public void onListItemClick(ListView l, View v, int position, long id) {  
	       
	  }  
	  
	  @Override  
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	    Bundle savedInstanceState) {  
		  adapter = new ArrayAdapter<String>(  
	     inflater.getContext(), android.R.layout.simple_list_item_1,  
	     numbers_text);  
	   setListAdapter(adapter);  
	   return super.onCreateView(inflater, container, savedInstanceState);  
	  }  
	 } 

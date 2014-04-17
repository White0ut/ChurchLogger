package edu.wmich.cs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AttendFrag extends Fragment {	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View android = inflater.inflate(R.layout.attend_frag, container,false);
		return android;
	}

}
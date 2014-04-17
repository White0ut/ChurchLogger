package edu.wmich.cs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public Fragment getItem(int i) {
		switch(i) {
		case 0:
			return new MessageListFrag();
		case 1:
			return new AttendFrag();
		}
	return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

}

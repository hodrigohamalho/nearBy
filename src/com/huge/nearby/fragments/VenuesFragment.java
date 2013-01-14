package com.huge.nearby.fragments;

import com.huge.nearby.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VenuesFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View venuesView = inflater.inflate(R.layout.venues_fragment, container, false);
		
		return venuesView;
	}

}

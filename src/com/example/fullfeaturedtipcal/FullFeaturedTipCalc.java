package com.example.fullfeaturedtipcal;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FullFeaturedTipCalc extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_featured_tip_calc);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.full_featured_tip_calc, menu);
		return true;
	}

}

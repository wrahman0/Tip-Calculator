package com.example.fullfeaturedtipcal;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

public class FullFeaturedTipCalc extends Activity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	
	private double billBeforeTip;
	private double tipAmount;
	private double finalBill;
	
	EditText billBeforeTipET;
	EditText tipAmountET;
	EditText finalBillET;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_featured_tip_calc);
		
		if (savedInstanceState == null){
			billBeforeTip = 0.0;
			tipAmount = 0.15;
			finalBill = 0.0;
		}else{
			billBeforeTip = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
			tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
			finalBill = savedInstanceState.getDouble(TOTAL_BILL);
		}
		
		billBeforeTipET = (EditText) findViewById(R.id.originalBillEditText);
		tipAmountET = (EditText) findViewById(R.id.tipEditText);
		finalBillET = (EditText) findViewById(R.id.finalBillEditText);
		
		billBeforeTipET.addTextChangedListener(billBeforeTipListener);
		
	}
	
	private TextWatcher billBeforeTipListener = new TextWatcher (){

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			try{
				billBeforeTip = Double.parseDouble(s.toString());
			}catch (NumberFormatException e){
				billBeforeTip = 0.0;
			}
			
			updateTipAndFinalBill();
		}
		
	};
	
	private void updateTipAndFinalBill(){
		double tipAmount = Double.parseDouble(tipAmountET.getText().toString());
		double finalBill = tipAmount*billBeforeTip + billBeforeTip;
		finalBillET.setText(String.format("%.02f", finalBill));
	}
	
	protected void onSavedInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putDouble(TOTAL_BILL, finalBill);
		outState.putDouble(BILL_WITHOUT_TIP, billBeforeTip);
		outState.putDouble(CURRENT_TIP, tipAmount);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.full_featured_tip_calc, menu);
		return true;
	}

}

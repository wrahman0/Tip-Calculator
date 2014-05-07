package com.example.fullfeaturedtipcal;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class FullFeaturedTipCalc extends Activity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";
	private static final String CURRENT_TIP = "CURRENT_TIP";
	
	private double billBeforeTip;
	private double tipAmount;
	private double finalBill;
	
	//Internal buff holders
	private double friendlyBuff = 0.0;
	private double opinionBuff = 0.0;
	private double specialsBuff = 0.0;
	
	//Internal buff factors
	private double friendlyFactor = 0.02;
	private double opinionFactor = 0.04;
	private double specialsFactor = 0.03;
	
	EditText billBeforeTipET;
	EditText tipAmountET;
	EditText finalBillET;
	
	CheckBox friendly;
	CheckBox opinion;
	CheckBox specials;
	
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
		tipAmountET.addTextChangedListener(tipListener);
		
		friendly = (CheckBox) findViewById(R.id.friendlyCheckBox);
		opinion = (CheckBox) findViewById(R.id.opinionCheckBox);
		specials = (CheckBox) findViewById(R.id.specialsCheckBox);
		setUpIntroCheckBoxes();
		updateTipAndFinalBill();
		
	}
	
	private void setUpIntroCheckBoxes () {
		
		friendly.setOnCheckedChangeListener (new CheckBox.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				
				if (friendly.isChecked()){
					friendlyBuff = billBeforeTip*friendlyFactor;
					updateTipAndFinalBill();
				}else{
					friendlyBuff = 0.0;
					updateTipAndFinalBill();
				}
				
			}
			
		});
		
		opinion.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (opinion.isChecked()){
					opinionBuff = billBeforeTip*opinionFactor;
					updateTipAndFinalBill();
				}else{
					opinionBuff = 0.0;
					updateTipAndFinalBill();
				}
				
				
			}
			
		});
		
		specials.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (specials.isChecked()){
					specialsBuff = billBeforeTip*specialsFactor;
					updateTipAndFinalBill();
				}else{
					specialsBuff = 0.0;
					updateTipAndFinalBill();
				}
				
			}
			
		});
		
		
	}
	
	private TextWatcher tipListener = new TextWatcher (){

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
				tipAmount = Double.parseDouble(s.toString());
			}catch (NumberFormatException e){
				tipAmount = 0.0;
			}
			updateTipAndFinalBill();
		}
		
	};
	
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
		
		double finalBill = tipAmount*billBeforeTip + billBeforeTip + friendlyBuff + opinionBuff + specialsBuff;
		//TODO:Append '$' sign
		finalBillET.setText('$' + String.format("%.02f", finalBill));
		
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

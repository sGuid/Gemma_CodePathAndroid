package com.example.gemtipcalculator2;

import java.text.DecimalFormat;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.view.inputmethod.EditorInfo;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {

	EditText edBillAmt;
	double tipValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edBillAmt = (EditText) findViewById(R.id.edBillAmt);		 
			 
//			//in OnCreate - for one button
			Button btnTip10 = (Button) findViewById(R.id.btnTip10);   //returns a few, pass in...R.id.
			btnTip10.setOnClickListener(this);  ///use this so impletment to MainActivity
			edBillAmt.setOnClickListener(this);
			//				public void onClick(View v){
//					
			}		
//				// multiple buttons - make local variatble
//				// OnClickListner callback = new ___ and call this???
					

	public void buttonTip10(View v){
	tipValue = 0.10;
	}
	
	public void buttonTip15(View v){
	tipValue = 0.15;	
	}
	
	public void buttonTip20(View v){
		tipValue = 0.20;
	}
	
//	public void CalculateTip(View v){
//	// On Button click, get tip value.
//		
//		double Tip = edBillAmt * tipPercent;
//		TextView TipDisplay = (edYourTipIs) findViewById(R.id.edYourTipIs);
//		TipDisplay.setText("Tip is" + Tip);	 
//	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
@Override
public void onClick(View v) {
	
	// TODO Auto-generated method stub
	
}



}

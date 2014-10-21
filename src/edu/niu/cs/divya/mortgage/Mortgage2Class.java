//****************************************************************************************************************
//Programmers: Divya Sri Kodali(Z1713778) & Anuraag Shah(Z1716167)
//Assignment 2
//Application to calculate information for a fixed mortgage
//****************************************************************************************************************
package edu.niu.cs.divya.mortgage;
import java.text.DecimalFormat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Class that holds the logic for the second screen.
public class Mortgage2Class extends Activity
{
	//variables that are used throughout the class
	EditText PrincipalM2, RateM2, LoanM2, PayMade;
	Button CalculateM2, ClearM2, Back;
	TextView RemPrincipalAns;
	Double Principal2, Rate2, Loan2,PaymentMade, fromValue, MonthlyInterestM2, RemainingPrincipal;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mortgage2);
		
		PrincipalM2 = (EditText)findViewById(R.id.etPrincipalS2);
		RateM2 = (EditText)findViewById(R.id.etRateS2);
		LoanM2 = (EditText)findViewById(R.id.etLoanS2);
		PayMade = (EditText)findViewById(R.id.etPaymentS2);
		CalculateM2 = (Button)findViewById(R.id.CalBtnS2);
		ClearM2 = (Button)findViewById(R.id.ClrBtnS2);
		Back = (Button)findViewById(R.id.BackBtnS2);
		RemPrincipalAns = (TextView)findViewById(R.id.RempriAnsS2);
		
		Intent sender = getIntent();
		fromValue = sender.getExtras().getDouble("MonthlyPayment");
		CalculateM2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {// on click this method calculates the remaining principal
				
				try{
				
				Principal2 = Double.valueOf(PrincipalM2.getText().toString());
				Rate2 = Double.valueOf(RateM2.getText().toString());
				Loan2 = Double.valueOf(LoanM2.getText().toString());
				PaymentMade = Double.valueOf(PayMade.getText().toString());
				MonthlyInterestM2 = Rate2/(12*100);
				RemainingPrincipal = Principal2 * (Math.pow((1+MonthlyInterestM2), PaymentMade))-(fromValue/MonthlyInterestM2)*((Math.pow((1+MonthlyInterestM2), PaymentMade))-1);
				DecimalFormat df = new DecimalFormat("#.00");
				RemPrincipalAns.setText("$"+df.format(RemainingPrincipal)); 
				}
				catch(Exception e)
				{
					Toast.makeText(Mortgage2Class.this,"Enter all fields!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
       Back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				finish();
			}
		});
       
       ClearM2.setOnClickListener(new View.OnClickListener()// clears all the text fields

  		 {	
  		@Override			
  		public void onClick(View v)

  		 {
  					
  			PrincipalM2.setText("");
  			RateM2.setText("");
  			LoanM2.setText("");
  			PayMade.setText("");
  			RemPrincipalAns.setText("");
  						
  		}
  				
  		});
		
	}// end on create

}

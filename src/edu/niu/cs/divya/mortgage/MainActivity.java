//****************************************************************************************************************
//Programmers: Divya Sri Kodali(Z1713778) & Anuraag Shah(Z1716167)
//Assignment 2
//Application to calculate information for a fixed mortgage
//****************************************************************************************************************

package edu.niu.cs.divya.mortgage;
import java.text.DecimalFormat;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Main class and logic for the first screen.
public class MainActivity extends Activity {
	//variables that are used throughout the class
	private static final int CODE_A = 1;
	EditText Principal, Rate, Loan;
	Button Calculate, Clear;
	TextView MonthlyPayAns, TotalAns;
	double Principal1, Rate1, Loan1, Power, MonthlyInterest, LoanAmortized, MonthlyPayment, TotalAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Principal = (EditText)findViewById(R.id.etPrincipalS1);
        Rate = (EditText)findViewById(R.id.etRateS1);
        Loan = (EditText)findViewById(R.id.etLoanS1);
        Calculate = (Button)findViewById(R.id.CalBtnS1);
        Clear = (Button)findViewById(R.id.ClrBtnS1);
        MonthlyPayAns = (TextView)findViewById(R.id.MonpayAnsS1);
        TotalAns = (TextView)findViewById(R.id.TotalAnsS1);
        
        Clear.setOnClickListener(new View.OnClickListener()// clears all the text fields

		 {	
		@Override			
		public void onClick(View v)

		 {
					
			Principal.setText("");
			Rate.setText("");
			Loan.setText("");
			MonthlyPayAns.setText("");
			TotalAns.setText("");
						
		}
				
		});
        
        Calculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) // on click this method calculates the monthly payment and total amount
			{
				try{
				Principal1 = Double.valueOf(Principal.getText().toString());
				Rate1 = Double.valueOf(Rate.getText().toString());
				Loan1 = Double.valueOf(Loan.getText().toString());
				MonthlyInterest = Rate1/(12*100);
				LoanAmortized = Loan1 * 12;
				Power = Math.pow((1+MonthlyInterest), -LoanAmortized);
				MonthlyPayment = Principal1 * (MonthlyInterest/(1-Power));	
				DecimalFormat df = new DecimalFormat("#.00");
				MonthlyPayAns.setText("$"+df.format(MonthlyPayment)); 
				TotalAmount = MonthlyPayment * LoanAmortized;
				TotalAns.setText("$"+df.format(TotalAmount)); 
				}
				catch(Exception e)
				{
					Toast.makeText(MainActivity.this,"Enter all fields!", Toast.LENGTH_LONG).show();
				}
			}
		});
        
        
    }// end on create


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	public boolean onMenuItemSelected(int fId, MenuItem item)
	{
		Intent i;
		
		if(item.getItemId()==R.id.both)
		{
			i = new Intent(this,Mortgage2Class.class);
			i.putExtra("MonthlyPayment", MonthlyPayment);
			startActivityForResult(i, CODE_A);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    
}

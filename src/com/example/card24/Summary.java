package com.example.card24;

import java.io.FileInputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Summary extends Activity {
	private TextView tv_winnum, tv_losenum, tv_totalnum;
	private String fileName = "summary.txt";
	private int winnum, losenum, totalnum;
	private ImageView imgBackToMain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		findViews();
		readFileData(fileName);
		display();
	}
	public void findViews(){
		tv_losenum = (TextView) findViewById(R.id.losenum);
		tv_winnum = (TextView) findViewById(R.id.winnum);
		tv_totalnum = (TextView) findViewById(R.id.totalnum);
		imgBackToMain = (ImageView)findViewById(R.id.backtomain2);
		imgBackToMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	public void display(){
		tv_losenum.setText(String.valueOf(losenum));
		tv_winnum.setText(String.valueOf(winnum));
		tv_totalnum.setText(String.valueOf(totalnum));
	}
	
	public void readFileData(String fileName){  
	     String result="";  
	     try {  
	        FileInputStream fin = openFileInput(fileName);  
	        int lenght = fin.available();
	        byte[] buffer = new byte[lenght];  
	        fin.read(buffer);
	        result = EncodingUtils.getString(buffer, "UTF-8");
	        if(result.length()==0){
	        	winnum =0 ;
	        	losenum = 0;
	        	totalnum = 0;
	        }else{
	        	String[] some = result.split(",");
	        	winnum = Integer.parseInt(some[0]);
	        	losenum = Integer.parseInt(some[1]);
	        	totalnum = Integer.parseInt(some[2]);
	        }
	        System.out.println(result+"+"+result.length());
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }    
	 }  
}

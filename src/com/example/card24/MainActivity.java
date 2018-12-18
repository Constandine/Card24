package com.example.card24;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn_start, btn_summary;
		btn_start = (Button)findViewById(R.id.enterclassic); 
		btn_summary = (Button)findViewById(R.id.entersummarize);
		btn_start.setOnClickListener(this);
		btn_summary.setOnClickListener(this);
	}
	@Override
	public void onClick(View v){
		if(v.getId() == R.id.enterclassic){
			Intent intent = new Intent(getBaseContext(), GamePage.class);
			startActivity(intent);
		}else if(v.getId() == R.id.entersummarize){
			Intent intent = new Intent(getBaseContext(), Summary.class);
			startActivity(intent);
		}
	}
}
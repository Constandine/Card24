package com.example.card24;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Starthere extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starthere);

		Button btn_starthere;
		btn_starthere = (Button)findViewById(R.id.starthere); 
		btn_starthere.setOnClickListener(this);
	}

	@Override
	public void onClick(View v){
		if(v.getId() == R.id.starthere){
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			startActivity(intent);
		}
		
	}
}
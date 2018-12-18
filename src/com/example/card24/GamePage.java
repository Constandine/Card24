package com.example.card24;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;

import org.apache.http.util.EncodingUtils;

import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;
import com.singularsys.jep.ParseException;

import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;  
import java.io.IOException;
public class GamePage extends Activity{
	private ImageView imgCard1, imgCard2, imgCard3, imgCard4, imgPlus, imgMinus, imgDivide, imgOpbrkt, imgClbrkt, imgMultiply, imgEqual, newgame, backspace, clear, imgBackToMain;
	private Card cards[] = new Card[4];
	private EditText txtFormula, txtResult;
	private String nowString="";
	private ArrayList<String> formulaList = new ArrayList<String>();
	private ArrayList<Integer> objectType = new ArrayList<Integer>();
	private int[] randomCards;
	private Jep jep = new Jep();
	private InputStream in;
	FileOutputStream fout;
    String fileName = "summary.txt";  
	int testmode = 0;
	int hascalculate = 0;
	private String[] unused = {
			"1 1 1 1","1 1 1 2","1 1 1 3","1 1 1 4","1 1 1 5","1 1 1 6","1 1 1 7","1 1 1 9","1 1 1 10","1 1 2 2","1 1 2 3","1 1 2 4","1 1 2 5","1 1 3 3","1 1 4 11","1 1 4 13","1 1 5 9","1 1 5 10","1 1 5 11","1 1 5 12","1 1 5 13","1 1 6 7","1 1 6 10","1 1 6 11","1 1 6 13","1 1 7 7","1 1 7 8","1 1 7 9","1 1 7 11","1 1 7 12","1 1 7 13","1 1 8 9","1 1 8 10","1 1 8 11","1 1 8 12","1 1 8 13","1 1 9 9","1 1 9 10","1 1 9 11","1 1 9 12","1 1 10 10","1 1 10 11","1 2 2 2","1 2 2 3","1 2 5 11","1 2 7 13","1 2 8 11","1 2 8 12","1 2 9 9","1 2 9 10","1 2 10 10","1 3 3 13","1 3 5 5","1 3 7 11","1 3 10 13","1 3 11 13","1 4 4 13","1 4 7 10","1 4 8 10","1 4 9 9","1 4 10 13","1 4 11 11","1 4 11 12","1 4 11 13","1 4 12 13","1 4 13 13","1 5 5 7","1 5 5 8","1 5 7 7","1 5 11 13","1 5 12 13","1 5 13 13","1 6 6 7","1 6 7 7","1 6 7 8","1 6 7 13","1 6 9 11","1 6 10 10","1 6 10 11","1 6 11 11","1 6 13 13","1 7 7 7","1 7 7 8","1 7 7 13","1 7 8 13","1 7 10 10","1 7 10 11","1 7 11 11","1 7 11 12","1 7 11 13","1 8 8 13","1 8 9 9","1 8 9 10","1 8 10 10","1 8 11 11","1 8 12 13","1 8 13 13","1 9 9 9","1 9 9 10","1 9 9 11","1 9 9 13","1 9 10 10","1 9 10 11","1 9 12 13","1 9 13 13","1 10 10 10","1 10 10 11","1 10 10 13","1 10 11 11","1 10 11 13","1 10 13 13","1 11 11 11","1 13 13 13","2 2 2 2","2 2 2 6","2 2 5 13","2 2 7 9","2 2 7 11","2 2 8 11","2 2 8 13","2 2 9 9","2 2 9 13","2 2 10 12","2 3 3 4","2 3 9 11","2 3 10 11","2 4 7 13","2 4 9 11","2 4 11 13","2 4 12 13","2 5 5 5","2 5 5 6","2 5 7 12","2 5 9 9","2 5 9 13","2 5 11 11","2 5 11 13","2 5 13 13","2 6 7 7","2 6 9 13","2 6 11 11","2 6 13 13","2 7 7 7","2 7 7 9","2 7 8 10","2 7 9 9","2 7 9 12","2 7 10 13","2 7 11 11","2 7 11 13","2 7 13 13","2 8 11 13","2 9 9 9","2 9 9 10","2 9 11 12","2 9 12 12","2 10 10 10","2 10 12 12","2 10 13 13","3 3 3 13","3 3 4 10","3 3 5 8","3 3 5 11","3 3 7 10","3 3 8 11","3 3 10 10","3 3 10 11","3 3 10 12","3 3 11 11","3 3 13 13","3 4 6 7","3 4 7 13","3 4 8 8","3 4 9 10","3 4 10 11","3 4 11 11","3 4 13 13","3 5 5 5","3 5 5 10","3 5 5 13","3 5 7 7","3 5 8 10","3 5 9 11","3 5 11 13","3 6 7 11","3 6 8 11","3 6 10 13","3 7 7 11","3 7 8 10","3 7 10 12","3 7 11 13","3 8 8 13","3 8 10 13","3 8 11 13","3 10 10 10","3 10 10 11","3 10 10 13","3 10 11 11","3 10 12 12","3 10 12 13","3 10 13 13","3 11 11 11","3 11 11 13","3 11 12 13","3 11 13 13","3 13 13 13","4 4 4 13","4 4 5 9","4 4 6 6","4 4 6 7","4 4 7 11","4 4 9 9","4 4 9 10","4 4 9 13","4 4 10 11","4 4 11 11","4 4 13 13","4 5 5 11","4 5 5 12","4 5 5 13","4 5 9 11","4 6 6 11","4 6 6 13","4 6 7 11","4 6 7 13","4 6 8 11","4 6 9 11","4 6 10 13","4 6 11 13","4 7 7 9","4 7 7 10","4 7 7 12","4 7 7 13","4 7 10 13","4 8 10 13","4 9 9 9","4 9 9 11","4 9 9 13","4 9 10 10","4 9 11 13","4 9 12 13","4 9 13 13","4 10 10 10","4 10 10 13","4 10 11 11","4 10 13 13","4 11 11 11","4 11 11 12","4 11 11 13","4 11 12 12","4 11 13 13","4 12 12 13","4 12 13 13","4 13 13 13","5 5 5 7","5 5 5 8","5 5 5 10","5 5 5 11","5 5 5 13","5 5 6 9","5 5 6 10","5 5 6 12","5 5 6 13","5 5 7 9","5 5 7 12","5 5 7 13","5 5 9 12","5 5 9 13","5 5 10 12","5 6 6 11","5 6 6 13","5 6 7 10","5 6 7 11","5 6 8 11","5 7 7 7","5 7 7 8","5 7 7 12","5 7 7 13","5 7 8 11","5 7 8 12","5 7 8 13","5 7 9 9","5 7 11 12","5 7 12 13","5 8 8 11","5 8 8 12","5 8 9 9","5 8 9 10","5 8 10 10","5 8 10 13","5 8 11 11","5 8 12 13","5 8 13 13","5 9 9 9","5 9 9 10","5 9 9 13","5 9 10 12","5 9 11 11","5 9 11 12","5 9 13 13","5 10 10 10","5 10 11 12","5 10 11 13","5 10 12 12","5 11 11 11","5 11 11 12","5 11 11 13","5 11 12 13","5 11 13 13","5 12 12 12","5 12 12 13","5 12 13 13","5 13 13 13","6 6 6 7","6 6 6 13","6 6 7 7","6 6 7 8","6 6 7 13","6 6 9 9","6 6 10 10","6 6 10 11","6 6 11 11","6 6 13 13","6 7 7 7","6 7 7 8","6 7 7 9","6 7 7 12","6 7 7 13","6 7 8 8","6 7 8 13","6 7 9 10","6 7 9 11","6 7 9 13","6 7 10 11","6 7 13 13","6 8 8 13","6 8 10 10","6 8 12 13","6 9 9 9","6 9 9 13","6 9 10 10","6 9 10 13","6 9 11 11","6 9 13 13","6 10 10 11","6 10 10 12","6 10 11 11","6 10 11 13","6 10 13 13","6 11 11 11","6 11 11 13","6 11 13 13","6 13 13 13","7 7 7 7","7 7 7 8","7 7 7 9","7 7 7 10","7 7 7 11","7 7 7 13","7 7 8 8","7 7 8 9","7 7 8 10","7 7 8 12","7 7 8 13","7 7 9 9","7 7 9 11","7 7 9 12","7 7 9 13","7 7 10 10","7 7 10 11","7 7 10 12","7 7 11 11","7 7 13 13","7 8 8 8","7 8 9 9","7 8 9 11","7 8 10 12","7 8 11 11","7 8 13 13","7 9 9 9","7 9 9 10","7 9 9 11","7 9 9 12","7 9 10 10","7 9 10 13","7 9 11 13","7 9 12 13","7 10 10 10","7 10 10 13","7 10 11 11","7 10 11 12","7 10 13 13","7 11 11 11","7 11 11 12","7 11 11 13","7 11 12 12","7 11 12 13","7 11 13 13","7 12 12 12","7 12 13 13","7 13 13 13","8 8 8 8","8 8 8 9","8 8 9 9","8 8 9 10","8 8 10 10","8 8 10 11","8 8 11 11","8 8 13 13","8 9 9 9","8 9 9 10","8 9 9 11","8 9 9 13","8 9 10 10","8 9 10 11","8 9 13 13","8 10 10 10","8 10 10 11","8 10 10 13","8 10 11 12","8 10 11 13","8 11 11 11","8 11 11 12","8 11 11 13","8 11 12 13","8 11 13 13","8 12 12 12","8 12 12 13","8 12 13 13","8 13 13 13","9 9 9 9","9 9 9 10","9 9 9 11","9 9 9 13","9 9 10 10","9 9 10 11","9 9 10 12","9 9 11 11","9 9 13 13","9 10 10 10","9 10 10 11","9 10 10 12","9 10 11 11","9 10 13 13","9 11 11 12","9 11 11 13","9 12 12 13","9 12 13 13","9 13 13 13","10 10 10 10","10 10 10 11","10 10 11 11","10 10 13 13","10 11 11 11","10 11 13 13","11 11 11 11","11 11 13 13","13 13 13 13"
	};
	private int winnum=0, losenum=0, totalnum=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_page);
		findViews();
		initialFourCards();
		addListener();
		readFileData(fileName);
	}
	
	
	
public void writeFileData(String filename){  
	try {
		FileOutputStream fout = openFileOutput(filename, MODE_PRIVATE); 
		String message;
		message = winnum+","+losenum+","+totalnum;
		byte[]  bytes = message.getBytes();  
        fout.write(bytes);
        fout.close();
       } catch (Exception e) {  
           e.printStackTrace();  
       }  
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
	public void initialFourCards(){
		if(testmode==5){
			testmode=0;
			randomCards[0] = 4;
			randomCards[1] = 5;
			randomCards[2] = 9;
			randomCards[3] = 10;
			Toast.makeText(getApplicationContext(), "Enter Test Mode.",Toast.LENGTH_SHORT).show();
		}else{
			randomCards = exportFourRandomNumber();
		}
		for(int i=0; i<4; i++){
			cards[i] = new Card(randomCards[i], i+1);
		}
		String []FileNames = new String[4];
		Context ctx = getBaseContext();
		int []resId = new int[4];
		for(int i=0; i<4; i++){
			FileNames[i] = cards[i].picname;
			resId[i] = getResources().getIdentifier(FileNames[i], "drawable", ctx.getPackageName());
		}
		imgCard1.setImageResource(resId[0]);
		imgCard2.setImageResource(resId[1]);
		imgCard3.setImageResource(resId[2]);
		imgCard4.setImageResource(resId[3]);
	}

	public void addListener(){
		clear.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(formulaList.size()==0){
					testmode++;
					clearTxt();
				}
				else{
					testmode=1;
					clearTxt();
				}
				if(testmode==5){
					initialFourCards();
					}
				hascalculate = 0;
			}
		});
		
		newgame.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				totalnum++;
				clearTxt();
				initialFourCards();
				hascalculate = 0;
			}
		});

		backspace.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String tempString = "";
				hascalculate = 0;
				if(formulaList.size()>0){
					switch (objectType.get(objectType.size()-1)) {
					case 1:
						cards[0].selected = false;
						imgCard1.setAlpha(255);
						break;
					case 2:
						cards[1].selected = false;
						imgCard2.setAlpha(255);
						break;
					case 3:
						cards[2].selected = false;
						imgCard3.setAlpha(255);
						break;
					case 4:
						cards[3].selected = false;
						imgCard4.setAlpha(255);
						break;
					}
					objectType.remove(objectType.size()-1);
					
					formulaList.remove(formulaList.size()-1);
					Iterator<String> it = formulaList.iterator();
					while (it.hasNext()) {
						tempString += it.next();
					}
					nowString = tempString;
					txtFormula.setText(nowString);
				}
			}
		});
		
		imgCard1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addCardToFormula(cards[0], 1);
			}
		});
		imgCard2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addCardToFormula(cards[1], 2);
			}
		});
		imgCard3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addCardToFormula(cards[2], 3);
			}
		});
		imgCard4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addCardToFormula(cards[3], 4);
			}
		});

		imgPlus.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addOperaToFormula("+");
			}
		});
		imgMinus.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addOperaToFormula("-");
			}
		});
		imgMultiply.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addOperaToFormula("*");
			}
		});
		imgDivide.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addOperaToFormula("/");
			}
		});
		imgOpbrkt.setOnClickListener(new OnClickListener(){
			@Override 
			public void onClick(View v) {
				addOperaToFormula("(");
			}
		});
		imgClbrkt.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				addOperaToFormula(")");
			}
		});
		
		imgBackToMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				writeFileData(fileName);
				finish();
			}
		});
		
		imgEqual.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				if(hascalculate==0){//Start calculate
				if(cards[0].selected == true && cards[1].selected == true && cards[2].selected == true && cards[3].selected == true){
					try{
						jep.parse(txtFormula.getText().toString());
						Object val = jep.evaluate();
						String tempresult = String.valueOf(val);
						DecimalFormat dcmFmt = new DecimalFormat("0.00");
						double doubleresult = Double.parseDouble(tempresult);
						int intresult = (int)Math.floor(doubleresult);
						
						if(intresult == doubleresult && intresult==24){
							winnum++;
							totalnum++;
							writeFileData(fileName);;
							txtResult.setText("√        "+intresult+"    ");
							hascalculate = 1;
						}else{
							losenum++;
							totalnum++;
							writeFileData(fileName);
							txtResult.setText("×        "+String.valueOf(dcmFmt.format(doubleresult))+"    ");
							hascalculate = 2;
						}
					} catch (ParseException e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "Please input the complete formula.",Toast.LENGTH_SHORT).show();
					}catch (JepException e) {
						 System.out.println("An error occurred: " + e.getMessage());	
					}
				}else{
					Toast.makeText(getApplicationContext(), "Please input the complete formula.",Toast.LENGTH_SHORT).show();
				}
				
				}else if(hascalculate==1){
					totalnum++;
					clearTxt();
					initialFourCards();
					hascalculate = 0;
				}else if(hascalculate==2){
					clearTxt();
					hascalculate = 0;
				}//END calculator
			
			}//END Onclick
		});		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			totalnum++;
			finish();
			writeFileData(fileName);;
		}
		return false;
		}

	
	protected void findViews(){
		imgCard1 = (ImageView)findViewById(R.id.imageCard1);
		imgCard2 = (ImageView)findViewById(R.id.imageCard2);
		imgCard3 = (ImageView)findViewById(R.id.imageCard3);
		imgCard4 = (ImageView)findViewById(R.id.imageCard4);
		imgPlus = (ImageView)findViewById(R.id.imagePlus);
		imgMinus = (ImageView)findViewById(R.id.imageMinus);
		imgDivide = (ImageView)findViewById(R.id.imageDivide);
		imgOpbrkt = (ImageView)findViewById(R.id.imageOpbrkt);
		imgClbrkt = (ImageView)findViewById(R.id.imageClbrkt);
		imgMultiply = (ImageView)findViewById(R.id.imageMultiply);
		imgEqual = (ImageView)findViewById(R.id.imageEqual);
		imgBackToMain = (ImageView)findViewById(R.id.backtomain);
		
 		clear = (ImageView)findViewById(R.id.btnClear);
		newgame = (ImageView)findViewById(R.id.btnNewGame);
		backspace =  (ImageView)findViewById(R.id.btnBackspace);
		txtFormula = (EditText)findViewById(R.id.txtFormula);
		txtResult = (EditText)findViewById(R.id.txtResult);	
	}
	
	/*
	 * 
	 * The method try to ouput four numbers that can actually
	 * calculate to 24
	 * 
	 */
	public int[] exportFourRandomNumber(){
		int[] a = new int[4];
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= 52; i++)
			list.add(i);
		int tempindex = -1;
		int [] b = new int[4];
		do{
			Collections.shuffle(list);
			Iterator<Integer> ite = list.iterator();
			for(int i=0; i<4; i++){
				a[i] = (Integer) ite.next();
				b[i] = a[i];
			}
			Arrays.sort(a);
			String temp = null;
			for(int i=0; i<4; i++){
				temp += a[i];
				if(i!=3)
					temp+=" ";
			}
			tempindex = Arrays.binarySearch(unused, temp);
		}while(tempindex>=0);
		
		return b;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void addToFormula(String s){
		nowString = nowString + s;
		formulaList.add(s);
		txtFormula.setText(nowString);
	}

	protected void addOperaToFormula(String s){
		int tempnum=0;
		for(int i=0; i<objectType.size();i++){
			if(objectType.get(i)>0)
				tempnum++;
		}
		if(s=="("){
			if(objectType.isEmpty() || (objectType.get(objectType.size()-1)<0)&&(objectType.get(objectType.size()-1)!=-3)){
				int numOpbrkt=0;
				for(int i=0; i<objectType.size(); i++){
					if(objectType.get(i)==-2)
						numOpbrkt++;
				}
				if(numOpbrkt<=3){
					objectType.add(-2);
					addToFormula(s);
				}
			}else{
				Toast.makeText(getApplicationContext(), "Invalid Input.",Toast.LENGTH_SHORT).show();
			}
		}else if (s==")"){
			if(objectType.isEmpty()==false &&(objectType.get(objectType.size()-1)>0 || objectType.get(objectType.size()-1)==-3)){
				int numOpbrkt=0, numClbrkt=0;
				for(int i=0; i<objectType.size(); i++){
					if(objectType.get(i)==-2)
						numOpbrkt++;
					else if(objectType.get(i)==-3)
						numClbrkt++;
				}
				if(numClbrkt < numOpbrkt){
					objectType.add(-3);
					addToFormula(s);
					}else{
						Toast.makeText(getApplicationContext(), "Invalid Input.",Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), "Invalid Input.",Toast.LENGTH_SHORT).show();
				}
		}else{
			if(objectType.isEmpty()==false && (objectType.get(objectType.size()-1)>0|| objectType.get(objectType.size()-1)==-3)&&tempnum!=4){
				objectType.add(-1);
				addToFormula(s);
			}else{
				Toast.makeText(getApplicationContext(), "Invalid Input.",Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void addCardToFormula(Card x, int position){
		if(x.selected == true){
			Toast.makeText(getApplicationContext(), "You have selected it..",Toast.LENGTH_SHORT).show();
		}
		else if(objectType.isEmpty() || (objectType.get(objectType.size()-1)==-1)||(objectType.get(objectType.size()-1)==-2)){
			objectType.add(position);
			x.selected = true;
			addToFormula(String.valueOf(x.showValue()));
			switch (position) {
			case 1:
				imgCard1.setAlpha(100);
				break;
			case 2:
				imgCard2.setAlpha(100);		
				break;
			case 3:
				imgCard3.setAlpha(100);
				break;
			case 4:
				imgCard4.setAlpha(100);
				break;
			default:
				break;
			}
		}else{
			Toast.makeText(getApplicationContext(), "Invaid card to put here.",Toast.LENGTH_SHORT).show();
		}
	}

	protected void clearTxt(){
		txtFormula.setText("");
		txtResult.setText("");
		nowString = "";
		formulaList.clear();
		objectType.clear();
		imgCard1.setAlpha(255);
		imgCard2.setAlpha(255);
		imgCard3.setAlpha(255);
		imgCard4.setAlpha(255);
		for(int i=0; i<4; i++)
			cards[i].selected = false;
	}
}
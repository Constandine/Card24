package com.example.card24;

public class Card {
	public
		/*
		*
		* number between 1 to 52 define the card
		* position between 1 to 4 define the position in screen
		* value between 1 to 12 express the number on the card
		* picname point to the pic represent this card 
		*
		*/
		int position, value, number;
		String picname;
		boolean selected, ifback;
	public
		Card(int x, int pos){
			position = pos;
			number = x;
			position = 0;
			value = (number%13!=0)?(number%13):13;
			selected = false;
			ifback = false;
			picname = outputFileName();
		}
	
	public int showPosition(){
		return position;
	}
	
	public int showValue(){
		return value;
	}
	public int showNumber(){
		return number;
	}

	public String outputFileName(){
		String Prefix = "cards";
		String Middle = "";
		String Suffix = "";

		int b = (number%13!=0)?(number%13):13;
		int a = (number - b)/13;
		switch (a){
			case 0:{
				Middle = "hearts";
				break;
			}
			case 1:{
				Middle = "diamonds";
				break;
			}
			case 2:{
				Middle = "club";
				break;
			}
			case 3:{
				Middle = "spade";
				break;
			}
		}

		if(b<=10 && b>=2){
			Suffix = String.valueOf(b);
		}else{
			switch (b){
				case 1:{
					Suffix = "a";
					break;
				}
				case 11:{
					Suffix = "j";
					break;
				}
				case 12:{
					Suffix = "q";
					break;
				}
				case 13:{
					Suffix = "k";
					break;
				}
			}
		}
		return Prefix+"_"+Middle+"_"+Suffix;
	}

}

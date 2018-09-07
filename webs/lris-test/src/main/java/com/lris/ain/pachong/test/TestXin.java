package com.lris.ain.pachong.test;

public class TestXin {

	public static void main(String[] args) {
	
		String love = "";
		double a=0;
		for (double y = 1.5; y > -1.5; y -= 0.1) {
		        for (double x = -1.5; x < 1.5; x += 0.05) {
		                a = x * x + y * y - 1;
		                love+= a * a * a - x * x * y * y * y <= 0 ? '*' : ' ';
		        }
		        love+="\n";
		}
		System.out.println(love);
	}
}

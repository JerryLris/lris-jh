package com.lris.ain.other.test;

import java.util.Random;

public class TodayLuck {

	private static int[] P1  = new int[6];
	
	private static int FLAG = 0;
	
	public static void main(String[] args) throws Exception {
			
		while(true) {
			
				int geta = getRed();
				if(geta == 0) {
					continue;
				}
				boolean a = true;
				for(int num:P1) {
					if(geta == num) {
						a = false;
						break;
					}else {
						
					}
				}
				if(a) {
					P1[FLAG] = geta;
					//System.out.println("第"+FLAG+"个数是:"+geta);
					FLAG +=1;
				}
				if(FLAG >5) {
					break;
				}
			}
			System.out.print("最终得到的数：");
			for(int num:P1) {
				System.out.print(num);
				System.out.print("-");
			}
			System.out.print("-");
			Random random = new Random();
			int b = random.nextInt(16);
			System.out.print(b);
		
	}
	
	private static int getRed() throws Exception{
		
		Random random = new Random();
		int a = random.nextInt(33);
		return a;
		
	}
}

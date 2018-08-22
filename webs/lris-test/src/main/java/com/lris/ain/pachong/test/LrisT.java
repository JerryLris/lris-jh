package com.lris.ain.pachong.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LrisT {

	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			//File file =  new File("G:\\\\lris\\haha\\num.txt");
			//FileReader fileReader = new FileReader(file);
			FileReader fileReader = new FileReader("G:\\\\lris\\haha\\num.txt");
			//System.out.println(file);
			System.out.println(fileReader);
			System.out.println(fileReader.read());
			FileWriter fileWriter = new FileWriter("G:\\\\lris\\haha\\num33.txt");
			 br = new BufferedReader(fileReader);
			 bw = new BufferedWriter(fileWriter);
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				bw.write(str);
				bw.newLine();
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(bw!=null) bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

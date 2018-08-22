package com.lris.ain.pachong.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestSystemReader {

	public static void main(String[] args) {
		BufferedReader br = null;
		InputStreamReader isr = null;
		InputStream is = null;
		
		try {
			is = new BufferedInputStream(System.in);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String str = null;
			while((str=br.readLine())!=null) {
				if(str.equals("exit")) break;
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br !=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

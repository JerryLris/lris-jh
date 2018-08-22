package com.lris.ain.pachong.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestWriter {

	public static void main(String[] args) {
		BufferedReader br = null;
		PrintWriter out = null;
		BufferedWriter bw = null;
		try {
			/*
			字符流用来读取字符数据，对于输入字符流而言，最为常用操作方法是使用BufferedReader
			因为该流有一个readLine()
			*/
			br = new BufferedReader(new FileReader("G:\\\\lris\\haha\\num.txt"));
			bw = new BufferedWriter(new FileWriter("G:\\\\lris\\haha\\num2.txt"));
			out = new PrintWriter(bw);
			String str = null;
			while((str=br.readLine())!=null) {
				System.out.println(str);
				//使用bw输出不会换行，得在调用bw.newLine()才能换行
				bw.write(str);
				bw.newLine();
				//out.println(str);
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
			if(out!=null) out.close();
		}
	}
}

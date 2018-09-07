package com.lris.ain.other.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils {

	public static void main(String[] args) {
		String filepath = "G:\\lris\\testfile";
		 File f = new File(filepath);
		 //FileUtils.readFileFilter(f, ".txt");
		 System.out.println("--");
		 if(f.isDirectory()) {
			 for(File file:FileUtils.readFileFilter(f, ".txt")) {
				 if(!file.isDirectory()) {
					 System.out.println(file.getName());
					 readFileToString(file);
				 }else {
					 
				 }
			 }
		 }
	}
	private static String readFileToString(File file) {
		BufferedReader br = null;
		StringBuffer stringBuffer = new StringBuffer();
		System.out.println(stringBuffer.toString());
		try {
			/*
			字符流用来读取字符数据，对于输入字符流而言，最为常用操作方法是使用BufferedReader
			因为该流有一个readLine()
			*/
			br = new BufferedReader(new FileReader(file));
			String str = null;
			while((str=br.readLine())!=null) {
				//System.out.println(str);
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
		}
		
		return null;
		
	}
	
	private static File[] readFileFilter(File file,final String suffix) {
		
		File[] fs = file.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				///过滤的结果是显示返回为true的值
				if(name.endsWith(suffix)) {
					return true;
				}
				return false;
			}
		});
		return fs;
	}
	
}

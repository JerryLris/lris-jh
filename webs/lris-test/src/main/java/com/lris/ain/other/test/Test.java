package com.lris.ain.other.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		 String filepath = "G:\\lris\\testfile";
		 File f = new File(filepath);//File类型可以是文件也可以是文件夹
		 //File[] fileList = f.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
		 
		 if(f.isDirectory()) {
			 for(File file:f.listFiles()) {
				 if(!file.isDirectory()) {
					 System.out.println(file.getName());
				 }
			 }
		 }
		 

			
		 /*List<File> wjList = new ArrayList<File>();//新建一个文件集合
		 for (int i = 0; i < fileList.length; i++) {
			 fileList[i].getName();
		    if (fileList[i].isFile()) {//判断是否为文件
		         wjList.add(fileList[i]);
		         fileList[i].getName();
		    }
		 }
		 System.out.println("-------------");
		 List<File> wjjList = new ArrayList<File>();//新建一个文件夹集合
		 for (int i = 0; i < fileList.length; i++) {
		    if (fileList[i].isDirectory()) {//判断是否为文件夹
		         wjjList .add(fileList[i]);
		    }
		 }*/
	}
}

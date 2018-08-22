package com.lris.ain.pachong.test;

import java.io.File;
import java.io.IOException;

public class TestFile01 {

	public static void main(String[] args) {
		try {
			File f = new File("G:\\\\lris\\\\haha\\1.txt");
			System.out.println(f.exists());
			//创建文件
			f.createNewFile();
			//判断文件是否存在
			System.out.println(f.exists());
			//删除文件
			//f.delete();
			//获取文件名
			System.out.println(f.getName());
			//获取文件夹路径
			System.out.println(f.getParent());
			//可以获取该文件的父类文件夹对象
			File pf = f.getParentFile();
			//判断文件是否是文件夹
			System.out.println(pf.isDirectory());
			
			File f2 = new File("G:\\lris\\haha\\a");
			//创建一个目录
			f2.mkdir();
			File f3 = new File("G:\\lris\\haha\\d/d/");
			//如果父目录不存在，会像创建父目录在创建相应的子目录
			f3.mkdirs();
			//如果删除的是目录，目录不为空就无法删除，
			/*
			 * 正确删除文件夹的操作是递归删除所有的文件和子文件夹
			 */
			f3.delete();
			//重命名文件--->可以用来做剪切
			f.renameTo(new File("G:\\lris\\haha\\11.txt"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

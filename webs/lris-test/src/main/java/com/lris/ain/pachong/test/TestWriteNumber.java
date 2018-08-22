package com.lris.ain.pachong.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestWriteNumber {

	public static void main(String[] args) {
		//TestWriteNumber1();
		TestWriteNumber2();
	}
	
	public static void TestWriteNumber1() {
		FileOutputStream fos = null;
		try {
			//11 12 15 29
			fos = new FileOutputStream("G:\\\\lris\\haha\\1.txt");
			String hello = "hello world";
			byte[] buf = hello.getBytes();
			String str = String.valueOf(299998877);
			//fos.write(str.getBytes(),0,str.getBytes().length);
			fos.write(buf);
			fos.write(buf, 0, buf.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos!=null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void TestWriteNumber2() {
		FileOutputStream fos = null;
		DataOutputStream dos =  null;
		DataInputStream dis = null;
		try {
			fos = new FileOutputStream("G:\\\\lris\\haha\\num.txt");
			//如果希望存储基本数据类型就使用DataOuputStream,也是过滤流
			dos = new DataOutputStream(fos);
			dos.writeInt(1212312312);
			dos.writeInt(222);
			dos.writeInt(42424);
			dos.writeInt(3343);
			dos.writeInt(34343);
			dos.writeInt(12);
			//从文件读取基本数据类型使用DataInputStream，同样是过滤流
			dis = new DataInputStream(new FileInputStream("G:\\\\lris\\haha\\num.txt"));
			int a = dis.readInt();
			System.out.println(a);
			System.out.println(dis.readInt());
			System.out.println(dis.readLong());
			System.out.println(dis.readInt());
			System.out.println(dis.readInt());
//			System.out.println(dis.readInt());//读到头就会报错
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos!=null) dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

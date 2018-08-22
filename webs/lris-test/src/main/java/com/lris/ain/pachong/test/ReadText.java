package com.lris.ain.pachong.test;

import java.io.File;
import java.io.FilenameFilter;

public class ReadText {

	public static void main(String[] args) {
		new ReadText().run();
	}
	
	public void run() {
		File f = new File("G:\\lris\\haha");
		System.out.println(f);
		//String[] fns = f.list(new JavaFileFilter());
		String[] fns = f.list();
		for(String fn:fns) {
			System.out.println(fn);
		}
		System.out.println("-------------------------------------");
		
		for(String fn:fns) {
			System.out.println(fn);
		}
		System.out.println("-------------------------------------");
		//列表一组文件对象
		File[] fs = f.listFiles();
		for(File file:fs) {
			System.out.println(file.getName()+":"+file.length());
		}
	}
	
	/**
	 * 如果这个类仅仅只是在某个类的内部可以访问，可以直接将该类写在类的内部
	 * 这个类在外部就无法访问。这种类叫做内部类
	 * 内部类要在static之后才能声明，所以不能在static的方法中使用内部类
	 * @author Administrator
	 *
	 */
	private class JavaFileFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			//过滤的结果是显示返回为true的值
			if(name.endsWith(".java")) return false;
			return true;
		}
	}
}

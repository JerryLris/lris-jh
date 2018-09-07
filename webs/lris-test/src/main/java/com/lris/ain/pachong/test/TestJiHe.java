package com.lris.ain.pachong.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestJiHe {

	public static void main(String[] args) {
		//testSet();
		//testMap();
		//testList();
		testList2();
	}
	
	public static void testSet() {
		Set<String> set = new HashSet<String>();
		set.add("123");
		set.add("234");
		set.add("456");
		set.add("333");
		boolean flag = set.add("123");
		System.out.println("set.add(\"123\"):"+flag);
		
		//创建一个迭代器，由于Collection通过iterator方法创建迭代器，所以所以集合类都拥有这个方法
		Iterator<String> it = set.iterator();
		//Iterator中有hasNext来检查元素是否还存在
		while(it.hasNext()) {
			//通过next方法可以获取下一个元素
			String str = it.next();
			System.out.println(str);
		}
		System.out.println("-----------------");
		for(String str:set) {
			System.out.println(str);
		}
		/*
		 * Set没有顺序，所以没有get方法
		 * for(int i=0;i<set.size();i++) {
			String str = set.g
		}*/
	}
	
	public static void testMap() {
				//Map存储的是键值对,key和value
				Map<Integer,String> maps = new HashMap<Integer,String>();
				//使用put方法添加值
				maps.put(1, "刘德华");
				maps.put(2, "张学友");
				maps.put(3, "周杰伦");
				maps.put(4, "李振");
				//使用get(key)可以得到value
				System.out.println(maps.get(4));
				
				//keySet可以将maps中的key转换为Set的一组列表
				Set<Integer> keys = maps.keySet();
				//遍历Set可以得到相应的key,使用maps.get(key)可以得到value
				for(Integer i:keys) {
					System.out.println(maps.get(i));
				}
				//此时不会增加，会覆盖
				maps.put(4, "黎明");
				System.out.println("-------------------------------------");
				//keySet可以将maps中的key转换为Set的一组列表
				keys = maps.keySet();
				//遍历Set可以得到相应的key,使用maps.get(key)可以得到value
				for(Integer i:keys) {
					System.out.println(maps.get(i));
				}
				
				System.out.println(maps.containsKey(4));
				System.out.println(maps.containsValue("周杰伦"));
	}
	public static void testList() {
		List<String> list = new ArrayList<String>();
		//通过add添加元素
		list.add("123");
		list.add("234");
		list.add("456");
		list.add("456");
		list.add("456");
		//通过remove移除元素
		
		boolean b = list.remove("234");
		System.out.println("list.remove(\"234\"):"+b);
		//nums.length
		//通过size()可以获取列表的长度
		for(int i=0;i<list.size();i++) {
			//通过get可以获取某一个位置的元素
			//get的元素是个Object的，所以需要进行强制类型转换
			String str = (String)list.get(i);
			System.out.println(str);
		}
	}
	public static void testList2() {
		//使用了泛型之后，说明这个list中只能添加String类型的值
				List<String> list = new ArrayList<String>();
				list.add("abc");
				list.add("123");
				list.add("444");
				
				List<Integer> list2 = new ArrayList<Integer>();
				list2.add(1);
				list2.add(2);
				list2.add(3);
				list2.add(4);
				list2.add(5);
				
				//Iterator<Integer> it = list2.iterator();
				/*for(;it.hasNext();) {
					int num = it.next();
					if(num==3) {
						list2.remove(new Integer(num));
					}
					System.out.println(num);
				}*/
				
				//此处如果仅仅使用remove(2)会移除下标为2的元素，如果要移除对象需要将2转换为Integer
				//list2.remove(new Integer(2));
				list2.remove(2);
				
				for(int i=0;i<list2.size();i++) {
					System.out.println(list2.get(i));
				}
				
				
				/*for(int i=0;i<list.size();i++) {
					String str = list.get(i);
					System.out.println(str);
				}*/
	}
}

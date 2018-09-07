package com.lris.ain.pachong.test;

public class TestArray {

	public static void main(String[] args) {
		//定义了一个变量nums可以存储4个int类型的数字
				int[] nums = new int[4];
//				int nums2[] = new int[6];//不建议使用
				nums[0] = 23;
				nums[2] = 33;
				nums[3] = 25;
				System.out.println(nums[0]+","+nums[1]+","+nums[2]+","+nums[3]);
				
				int[] nums2 = {23,45,67,88,34,55,57,778,88,3,43,12,44,36,5,76,234,45,4,64,64,45,45,45,22};
				/*
				 * 定义两个数组，大小均为3，使用两种方式定义，并且输出值
				 * */
				
				for(int i=0;i<nums2.length;i++) {
					System.out.println(nums2[i]);
				}
				
		/**
		 * 定义一个50个长度的数组，并且随机产生0-100之间的为这个数组设置满值，并且遍历数组
		 * (int)(Math.random()*100)
		 */
		int[] nums11 = new int[50];
		for(int i=0;i<nums11.length;i++) {
			nums11[i] = (int)(Math.random()*100);
		}
		
		for(int i=0;i<nums11.length;i++) {
			//System.out.println(nums[i]);
		}
	}
}

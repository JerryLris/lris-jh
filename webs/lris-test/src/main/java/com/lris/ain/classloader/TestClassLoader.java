package com.lris.ain.classloader;

public class TestClassLoader {
/*    public static void main(String[] args) throws ClassNotFoundException {
        // 当A对象被创建的时候 先会执行静态代码块,再实执行A的构造方法
        A a = new A();
        System.out.println(a.width);
        // 输出顺序为  创建初始化类A--> 创建A对象  --> width = 300
        A a2 = new A();
        // 只会打印 创建A对象，不会再加载第二次
   }*/
	
	public static void main(String[] args) {
		
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(ClassLoader.getSystemClassLoader());
		
		 // 委托给父类加载器 这时候进入系统的加载器中，会一层一层的往上送
		// ClassLoader parent = this.getParent();
		// parent.loadClass();
	}
}

class A {
	
    public static int width = 100;
    public static final int MAX = 200;
    static {
        System.out.println("静态初始化类A");
        width = 300;
    }
    public A(){
        System.out.println("创建A对象");
    }
}

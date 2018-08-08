package com.lris.ain.back.config;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PackageUtil {
	
	static Logger log = LogManager.getLogger(PackageUtil.class);
	
	public static void main(String[] args){
		Set<Class<?>> clazz = PackageUtil.getClasses("com.lris.ain.back","Action.class");
		//Set<Class<?>> clazz = PackageUtil.getClasses("jar:file:/home/duke/duke.jar!/","Action.class");
		Iterator<Class<?>> iter = clazz.iterator();
		while(iter.hasNext()){
			Class<?> c = iter.next();
			System.out.println(c.getName());
		}
		
		Set<Class<?>> clazzS = PackageUtil.getClasses("com.lris.ain.core.variables","Variable.class");
		Iterator<Class<?>> iterS = clazzS.iterator();
		while(iterS.hasNext()){
			Class<?> c = iterS.next();
			System.out.println(c.getName());
		}
	}
	
	 public static Set<Class<?>> getClasses(String pack,String classtype) { 
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();  
		boolean recursive = true;   // 是否循环迭代  
        String packageName = pack;  
        String packageDirName = packageName.replace('.', '/');  
        Enumeration<URL> dirs;  
        try {  
            dirs = Thread.currentThread().getContextClassLoader().getResources( packageDirName);              
            while (dirs.hasMoreElements()) {  
                URL url = dirs.nextElement(); 
                String protocol = url.getProtocol();  
                if ("file".equals(protocol)) { 
                	log.info("file");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8"); 
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes,classtype);  
                } else if ("jar".equals(protocol)) { 
                	log.info("jar");
                    JarFile jar;  
                    try { 
                    	JarURLConnection jarconn = (JarURLConnection)url.openConnection();
                        jar = jarconn.getJarFile();
                        Enumeration<JarEntry> entries = jar.entries();  
                        while (entries.hasMoreElements()) {  
                            
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            // 如果是以/开头的  
                            if (name.charAt(0) == '/') {  
                                // 获取后面的字符串  
                                name = name.substring(1);  
                            }  
                            // 如果前半部分和定义的包名相同  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                // 如果以"/"结尾 是一个包  
                                if (idx != -1) {  
                                    // 获取包名 把"/"替换成"."  
                                    packageName = name.substring(0, idx)  
                                            .replace('/', '.');  
                                }  
                                // 如果可以迭代下去 并且是一个包  
                                if ((idx != -1) || recursive) {  
                                    // 如果是一个.class文件 而且不是目录  
                                    if (name.endsWith(classtype)  
                                            && !entry.isDirectory()) {  
                                        // 去掉后面的".class" 获取真正的类名  
                                        String className = name.substring(  
                                                packageName.length() + 1, name  
                                                        .length() - 6);  
                                        try {  
                                            // 添加到classes  
                                            classes.add(Class  
                                                    .forName(packageName + '.'  
                                                            + className));  
                                        } catch (ClassNotFoundException e) { 
                                            e.printStackTrace();  
                                        }  
                                    }  
                                }  
                            }  
                        }  
                    } catch (IOException e) {  
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");  
                        e.printStackTrace();  
                    }  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return classes;  
    }  
	 
	 public static void findAndAddClassesInPackageByFile(String packageName,  
	            String packagePath, final boolean recursive, Set<Class<?>> classes,final String classtype) {  
	         
	        File dir = new File(packagePath);  
	        
	        if (!dir.exists() || !dir.isDirectory()) {  
	            return;  
	        }  
	          
	        File[] dirfiles = dir.listFiles(new FileFilter() { 
	            public boolean accept(File file) {  
	                return (recursive && file.isDirectory())  
	                        || (file.getName().endsWith(classtype));  //扫描controller 
	            }  
	        }); 
	        
	        
	        for (File file : dirfiles) {  
	            
	            if (file.isDirectory()) {  
	                findAndAddClassesInPackageByFile(packageName + "."  
	                        + file.getName(), file.getAbsolutePath(), recursive,  
	                        classes,classtype);  
	            } else { 
	                String className = file.getName().substring(0, file.getName().length() - 6);  
	                try {  
	                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));    
	                 } catch (ClassNotFoundException e) { 
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	    }  
}

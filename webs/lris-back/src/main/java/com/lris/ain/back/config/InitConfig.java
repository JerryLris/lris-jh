package com.lris.ain.back.config;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.lris.ain.back.auth.Module;
import com.lris.ain.back.auth.Right;
import com.lris.ain.back.sys.service.FuncService;
import com.lris.ain.back.sys.service.VariableService;
import com.lris.ain.back.sys.vo.Func;
import com.lris.ain.back.sys.vo.Variable;
import com.lris.ain.core.variables.VariableBean;
import com.lris.ain.core.variables.VariableTypeAnnotation;


@Component
public class InitConfig implements InitializingBean{

	Logger log = LogManager.getLogger(InitConfig.class);
	
	@Resource
	private FuncService funcService;
	
	@Resource
	private VariableService varService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("启动配置类，初始化权限等信息");
		initRight();
		initVariable();
	}

	private void initVariable(){
		Set<Class<?>> clazz = PackageUtil.getClasses("com.lris.ain.core.variables","Variable.class");
		Iterator<Class<?>> iter = clazz.iterator();
		List<Variable> list = new ArrayList<Variable>();
		while(iter.hasNext()){
			Class<?> c = iter.next();
			VariableTypeAnnotation var = c.getAnnotation(VariableTypeAnnotation.class);
			if(var == null){
				continue;
			}
			
			for(Object obj:c.getEnumConstants()){
				if(obj instanceof VariableBean){
					VariableBean vb = (VariableBean)obj;
					Variable vars = new Variable(vb.getKey(),vb.getDescription(),vb.getValue(),vb.getType());
					list.add(vars);
				}
			}
		}
		if(list!=null&&list.size()>0){
			try {
				varService.newVariable(list.toArray(new Variable[list.size()]));
			} catch (Exception e) {
				e.printStackTrace();
				log.warn(e.getMessage());
			}
		}
	}
	
	private void initRight(){
		Set<Class<?>> clazz = PackageUtil.getClasses("com.lris.ain.back","Action.class");
		Iterator<Class<?>> iter = clazz.iterator();
		List<Func> list = new ArrayList<Func>(); 
		while(iter.hasNext()){
			Class<?> c = iter.next();
			Module mode = c.getAnnotation(Module.class);
			if(mode==null){
				continue;
			}
			String pkey = mode.pkey();
			String key = mode.key();
			String name = mode.name();
			Func mfunc = new Func(pkey+"."+key,pkey,key,name,1);
			list.add(mfunc);
			Method[] method = c.getMethods();
			for(int i=0;i<method.length;i++){
				Method m = method[i];
				Right right = m.getAnnotation(Right.class);
				if(right==null){
					continue;
				}
				String rkey = right.key();
				String rname = right.name();
				Func ofunc = new Func(key+"."+rkey,key,rkey,rname,2);
				list.add(ofunc);
			}
		}
		if(list.size()>0){
			try {
				funcService.newFunc(list.toArray(new Func[list.size()]));
			} catch (Exception e) {
				e.printStackTrace();
				log.warn(e.getMessage());
			}
		}
	}
}

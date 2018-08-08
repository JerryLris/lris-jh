package com.lris.ain.back.enums;

import org.springframework.util.StringUtils;

/**
 * 使用状态
 * @author tom
 *
 */
public enum UseState {
	QY("启用"),
	TY("停用");
    
    protected final String cname;

    private UseState(String cname){
        this.cname = cname;
    }
    
    public String getCname() {
        return cname;
    }
   
    public static final UseState parse(String value) {
        if(StringUtils.isEmpty(value)){
            return null;
        }
        try{
            return UseState.valueOf(value);
        }catch(Throwable t){
            return null;
        }
    }
}

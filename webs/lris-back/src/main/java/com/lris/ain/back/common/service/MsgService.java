package com.lris.ain.back.common.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lris.ain.back.common.domain.MsgType;
import com.lris.ain.back.common.repository.MsgRepository;
import com.lris.ain.back.common.variable.LetterVariable;
import com.lris.ain.back.entities.B1091;
import com.lris.ain.core.exception.ParamException;
import com.lris.ain.core.net.HttpHelper;
import com.lris.ain.core.utils.parser.BooleanParser;
import com.lris.ain.core.utils.parser.IntegerParser;


/**
 * 短信、邮件、站内信服务
 *
 */
@Service
public class MsgService {

	@Resource
	private MsgRepository repository;
	
	/**
	 * 发送短信
	 * @param type
	 * @param msg
	 * @param tels
	 */
	
	public void sendSms(int type,String msg,String... tels) throws Exception{
		
		
		B1091 tb = new B1091();
		tb.setF02(type);
		tb.setF03(msg);
		String sF04 = "";
		if(tels!=null&&tels.length>0){
			for (String tel : tels) {
				sF04 = sF04 + tel + ",";
			}
			sF04 = sF04.substring(0,sF04.length()-1);
			tb.setF04(sF04);
			tb.setF05(tels.length);
		}
		tb.setF09("CL");
		
		if(MsgType.AUTH == type||MsgType.BIND_CODE == type||MsgType.SIGN_CODE == type) {
			int okcount = IntegerParser.parse(repository.getSysVal(LetterVariable.AUTH_SEND_COUNT.getKey()));
			int sentcount = repository.getSentCount(tb);
			if(sentcount >= okcount) {
				tb.setF03(tb.getF03()+"(未发送)");
				repository.sendSms(tb);
				throw new ParamException("验证码短信超次数限制，请联系运营");
			}
		}
		
		int id = repository.sendSms(tb);
		
		try{
			boolean qymsg = BooleanParser.parse(repository.getSysVal(LetterVariable.QY_SEND_MSG.getKey()));
			if(qymsg){
				String url = repository.getSysVal(LetterVariable.SEND_CL_URL.getKey());
				String account = repository.getSysVal(LetterVariable.SEND_CL_USERNAME.getKey());
				String pwd = repository.getSysVal(LetterVariable.SEND_CL_PWD.getKey());
		    	Map<String,String> params = new HashMap<String,String>();
		    	params.put("account", account);
		    	params.put("pswd", pwd);
		    	params.put("mobile",sF04);
		    	params.put("msg", msg);
		    	params.put("needstatus", "true");
		    	String resultCode = new HttpHelper().doPost(url, params,null);
		    	if(StringUtils.isNotBlank(resultCode)){
		    		resultCode = resultCode.replaceAll("\\n", ",");
		    		String[] rs = resultCode.split(",");
		    		String msgid = "";
		    		if(rs!=null && rs.length>=3){
		    			msgid = rs[2];
		    		}
		    		repository.updateSmsReturn(id, resultCode, msgid);
		    	}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 发送邮件
	 * @param type
	 * @param subject
	 * @param content
	 * @param addresses
	 */
	@Transactional
	public void sendMail(int type, String subject, String content, String[] addresses) throws Exception{
		repository.sendMail(type, subject, content, addresses);
	}
	
	/**
	 * 发送站内信
	 * @param userid
	 * @param subject
	 * @param content
	 */
	@Transactional
	public void sendLetter(int userid, String subject, String content) throws Exception{
		repository.sendLetter(userid, subject, content);
	}
	
}
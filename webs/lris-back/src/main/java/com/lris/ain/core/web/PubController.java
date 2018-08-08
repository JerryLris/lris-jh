package com.lris.ain.core.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;
import com.lris.ain.core.exception.AuthException;
import com.lris.ain.core.exception.ParamException;
import com.lris.ain.core.exception.ServiceException;
import com.lris.ain.core.query.Page;
import com.lris.ain.core.query.Query;
import com.lris.ain.core.utils.TokenHandler;

public abstract class PubController {

	protected Logger logger = null;
	protected String returnurl = "";
	
	/**
	 * 获取查询条件
	 * @param request
	 * @return
	 */
	protected Map<String,String> getQueryFilter(HttpServletRequest request){
		Map<String,String> filter = new HashMap<String,String>();
		Map<String,String[]> param = request.getParameterMap();
		Set<Entry<String, String[]>> set = param.entrySet();  
        Iterator<Entry<String, String[]>> it = set.iterator();
        while(it.hasNext()){
        	Entry<String, String[]> entry = it.next();
        	filter.put(entry.getKey(), (entry.getValue()!=null&&entry.getValue().length>0)?entry.getValue()[0]:null);
        }
        return filter;
	}
	
	/**
	 * 获取分页信息
	 * @param req
	 * @return
	 */
	protected Page getPage(HttpServletRequest req){
		Page page = new Page();
		int pageIndex = Integer.parseInt(StringUtils.isEmpty(req.getParameter("pageIndex"))?"1":req.getParameter("pageIndex"));
		int totalCount = Integer.parseInt(StringUtils.isEmpty(req.getParameter("totalCount"))?"0":req.getParameter("totalCount"));
		page.setPageIndex(pageIndex);
		page.setTotalCount(totalCount);
		if(req.getParameter("pageSize")!=null){
			page.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
		}
		return page;
		
	}
	
	protected Query getQuery(HttpServletRequest req){
		Query  query  = new Query();
		query.setFilter(this.getQueryFilter(req));
		query.setPage(this.getPage(req));
		return query;		
	}
	
	@ExceptionHandler 
	protected String doExp(HttpServletRequest request,HttpServletResponse response,Exception ex){
		ex.printStackTrace();
		logger.error(ex.toString());
		boolean isajax = request.getHeader("accept").indexOf("application/json") > -1
	             || (request.getHeader("X-Requested-With")!= null 
	             && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1) ;
		
		if(isajax){
			RespResult rs = new RespResult();
			if(ex instanceof AuthException){
				rs.setRs(RespResult.FAIL_AUTH);
				rs.setMsg(ex.getMessage());
			}else if(ex instanceof ParamException ){
				rs.setRs(RespResult.FAIL_PARAM);
				rs.setMsg(ex.getMessage());
			}else if(ex instanceof ServiceException ){
				rs.setRs(RespResult.FAIL_SERVICE);
				rs.setMsg(ex.getMessage());
			}else{
				rs.setRs(RespResult.FAIL_OTHER);
				rs.setMsg("操作失败");
			}
			
			Gson gson = new Gson();
			try {
				response.getWriter().print(gson.toJson(rs));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			String msg = "操作失败";
			try {
				if(ex instanceof AuthException 
						|| ex instanceof ParamException
						|| ex instanceof ServiceException
						){
					msg = ex.getMessage();
				}
				msg = URLEncoder.encode(msg, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(!StringUtils.isEmpty(returnurl)){
				return "redirect:"+returnurl+"?errormsg="+msg;
			}else{
				return "redirect:/error/serviceerror?errormsg="+msg;
			}
		}
		
	}
	
	/**
	 * ajax请求判断
	 * @param request
	 * @throws Exception
	 */
	protected void needAjax(HttpServletRequest request) throws Exception{
		boolean isajax = request.getHeader("accept").indexOf("application/json") > -1
	             || (request.getHeader("X-Requested-With")!= null 
	             && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1) ;
		if(!isajax){
			throw new ServiceException("非法请求");
		}
	}

	/**
	 * 判断是否是重复提交form
	 * @param req
	 * @return
	 */
	protected void checkFormToken(HttpServletRequest req) throws Exception{
		boolean isok =  TokenHandler.verify(req.getSession(), req.getParameter(TokenHandler.TOKEN_NAME));
		if(!isok){
			throw new ServiceException("请不要重复提交请求");
		}
	}
	
	/**
	 * 重复请求判断
	 * @param req
	 * @throws Exception
	 */
	protected boolean getFormToken(HttpServletRequest req) {
		return TokenHandler.verify(req.getSession(), req.getParameter(TokenHandler.TOKEN_NAME));
	}
	
	protected void genFormToken(HttpServletRequest req,Model model){
		model.addAttribute("formtoken",TokenHandler.hidden(req.getSession()));
	}
	
	protected String genFormToken(HttpServletRequest req){
		return TokenHandler.hidden(req.getSession());
	}
	
	protected String genGetToken(HttpServletRequest req){
		return TokenHandler.urlparam(req.getSession());
	}
	
	protected String encodeURL(String url){
		try {
			return  URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	protected String getRemoteAddr(HttpServletRequest req){
		String remoteaddr = req.getHeader("x-for");
		if (StringUtils.isEmpty(remoteaddr)) {
			remoteaddr = req.getRemoteAddr();
	    }
		return remoteaddr;
	}
	
/*	protected B1302 getReqInfo(HttpServletRequest req){
		B1302 B1302 = new B1302();
		B1302.setF05(getRemoteAddr(req));
		B1302.setF09(req.getHeader("User-Agent"));
		String source = "";
		if(req.getSession()!=null){
			source = (String)req.getSession().getAttribute("source");
		}
		if(StringUtils.isEmpty(source)){
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie:cookies){
				if("source".equals(cookie.getName())){
					source = cookie.getValue();
					break;
				}
			}
		}
		B1302.setF10(source);
		return B1302;
	}*/
	
	protected String getCookie(HttpServletRequest req,String key){
		String val = null;
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if("source".equals(cookie.getName())){
					val = cookie.getValue();
					break;
				}
			}
		}
		return val;
	}
	
	protected void setCookie(HttpServletResponse response,String key,String val,int maxAge){
		Cookie cookie = new Cookie(key,val);
		//cookie.setHttpOnly(true);
		cookie.setPath("/");
		if(maxAge>0){
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}
}

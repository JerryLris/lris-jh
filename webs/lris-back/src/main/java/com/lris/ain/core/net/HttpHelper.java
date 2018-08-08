package com.lris.ain.core.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 * http请求工具类
 * @author tom
 *
 */
public class HttpHelper {

	
	
	/**
	 * 处理get请求,http协议
	 * @param url
	 * @return
	 */
	public String doGet(String url,Map<String,String> header){
		String html = null;
		CloseableHttpClient httpclient = null;
		if(url.startsWith("https")){
			httpclient = createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		} 
    	HttpGet httpget = new HttpGet(url);
    	CloseableHttpResponse response = null;
    	try {
    		if(header!=null){
    			Set<String> keys = header.keySet();
    			Iterator<String> iter = keys.iterator();
    			while(iter.hasNext()){
    				String key = iter.next();
    				httpget.setHeader(key, header.get(key));
    			}
    		}
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			html = EntityUtils.toString(entity,"UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return html;
	}
	
	/**
	 * 处理post请求，http协议
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPost(String url,Map<String,String> params,Map<String,String> header){
		String html = null;
		List<NameValuePair> form = new ArrayList<NameValuePair>();
		if(params!=null){
			Set<String> keys = params.keySet();
			Iterator<String> iter = keys.iterator();
			while(iter.hasNext()){
				String key = iter.next();
				NameValuePair keyval = new BasicNameValuePair(key,params.get(key));
				form.add(keyval);
			}
		}
		CloseableHttpClient httpclient = null;
		if(url.startsWith("https")){
			httpclient = createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		} 
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(3000000).build();
		httppost.setConfig(requestConfig);
		UrlEncodedFormEntity entity;
		CloseableHttpResponse response = null;
		try {
			if(header!=null){
    			Set<String> keys = header.keySet();
    			Iterator<String> iter = keys.iterator();
    			while(iter.hasNext()){
    				String key = iter.next();
    				httppost.setHeader(key, header.get(key));
    			}
    		}
			entity = new UrlEncodedFormEntity(form,"UTF-8");
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);
			HttpEntity rs = response.getEntity();
			html = EntityUtils.toString(rs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return html;
	}
	
	/**
	 * 处理post请求，http协议
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPostStream(String url,String body){
		String html = null;
		CloseableHttpClient httpclient = null;
		if(url.startsWith("https")){
			httpclient = createSSLClientDefault();
		}else{
			httpclient = HttpClients.createDefault();
		}
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(3000000).build();
		httppost.setConfig(requestConfig);
		InputStreamEntity entity = null;
		CloseableHttpResponse response = null;
		try {
			entity = new InputStreamEntity(new ByteArrayInputStream(body.getBytes()));
			httppost.setEntity(entity);
			
			response = httpclient.execute(httppost);
			HttpEntity rs = response.getEntity();
			html = EntityUtils.toString(rs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return html;
		
	}
	
	/**
	 * 处理post请求，http协议
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPostStreamWithTLSV(String url,String body){
		String html = null;
		CloseableHttpClient httpclient = null;
		if(url.startsWith("https")){
			httpclient = createSSLClientTLSV();
		}else{
			httpclient = HttpClients.createDefault();
		}
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(3000000).build();
		httppost.setConfig(requestConfig);
		InputStreamEntity entity = null;
		CloseableHttpResponse response = null;
		try {
			entity = new InputStreamEntity(new ByteArrayInputStream(body.getBytes()));
			httppost.setEntity(entity);
			
			response = httpclient.execute(httppost);
			HttpEntity rs = response.getEntity();
			html = EntityUtils.toString(rs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return html;
		
	}
	
	/**
	 * 处理post请求，http协议
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPostString(String url,String body){
		String html = null;
		HttpClient httpclient = null;
		if(url.startsWith("https")){
			try {
				httpclient = new SSLClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			httpclient = HttpClients.createDefault();
		}
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(3000000).build();
		httppost.setConfig(requestConfig);
		StringEntity entity = null;
		HttpResponse response = null;
		try {
			entity = new StringEntity(URLEncoder.encode(body));
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);
			HttpEntity rs = response.getEntity();
			html = EntityUtils.toString(rs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
		return html;
		
	}
	
	/**
	 * 下载文件
	 * @param url
	 * @param header
	 * @return
	 */
	public byte[] doGetFile(String url,Map<String,String> header){
		InputStream  ins = null;
		HttpGet httpget = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(3000000).build();
		httpget.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		try {
			if(header!=null){
    			Set<String> keys = header.keySet();
    			Iterator<String> iter = keys.iterator();
    			while(iter.hasNext()){
    				String key = iter.next();
    				httpget.setHeader(key, header.get(key));
    			}
    		}
			response = httpclient.execute(httpget);
			HttpEntity rs = response.getEntity();
			ins = rs.getContent();
			int len = 0; 
			byte[] buffer = new byte[1024];
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	        while((len = ins.read(buffer)) != -1) {    
	            bos.write(buffer, 0, len);    
	        }    
	        bos.close(); 
			return bos.toByteArray();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private  CloseableHttpClient createSSLClientDefault(){
		try {
				SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	                 
					@Override
					public boolean isTrusted(
							X509Certificate[] chain,
							String authType)
							throws CertificateException {
						return true;
					}
	             }).build();
	             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
	             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	         } catch (KeyManagementException e) {
	             e.printStackTrace();
	         } catch (NoSuchAlgorithmException e) {
	             e.printStackTrace();
	         } catch (KeyStoreException e) {
	             e.printStackTrace();
	         }
	         return  HttpClients.createDefault();
	}
	
	private  CloseableHttpClient createSSLClientTLSV(){
		try {
			SSLContext ctx = SSLContext.getInstance("TLSv1.2");  
            X509TrustManager tm = new X509TrustManager() {  
                @Override  
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
                }  
  
                @Override  
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
                }  
  
                @Override  
                public X509Certificate[] getAcceptedIssuers() {  
                    return null;  
                }  
            };  
            ctx.init(null, new TrustManager[] { tm }, null);  
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(ctx);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  HttpClients.createDefault();
	}
	
	public String doPostStringBySSL(String url,String body){
		String html = null;
		HttpClient httpclient = null;
		if(url.startsWith("https")){
			try {
				httpclient = createSSLClientDefault();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			httpclient = HttpClients.createDefault();
		}
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(3000000).build();
		httppost.setHeader("Content-Type", "application/json");
		httppost.setConfig(requestConfig);
		StringEntity entity = null;
		HttpResponse response = null;
		try {
			entity = new StringEntity(body, "UTF-8");
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);
			HttpEntity rs = response.getEntity();
			html = EntityUtils.toString(rs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
		return html;
	}
	
	public String doPostFileBySSL(String url,MultipartEntityBuilder entity){
		String html = null;
		HttpClient httpclient = null;
		if(url.startsWith("https")){
			try {
				httpclient = createSSLClientDefault();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			httpclient = HttpClients.createDefault();
		}
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(3000000).build();
		httppost.setConfig(requestConfig);
		HttpResponse response = null;
		try {
			httppost.setEntity(entity.build());
			response = httpclient.execute(httppost);
			HttpEntity rs = response.getEntity();
			html = EntityUtils.toString(rs,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}
		return html;
	}
}

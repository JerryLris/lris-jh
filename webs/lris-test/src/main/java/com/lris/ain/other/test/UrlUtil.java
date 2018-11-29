package com.lris.ain.other.test;

import java.io.UnsupportedEncodingException;

/**
 * url转码、解码
 *
 */
public class UrlUtil {
    private final static String ENCODE = "UTF-8"; 
    /**
     * URL 解码
     *
     * @return String
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     *
     * @return String
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @return void
     */
    public static void main(String[] args) {
        String str = "https://open.shujumohe.com/box/yys?box_token=40DEAF1E638F468284695E2B2E184F10&real_name=刘坚&cd=https://www.baidu.com/&identity_code=430424199202224613&identity_code=430424199202224613&passback_params=32151##1arr_pass_hidreal_name,identity_code,user_mobile";
        System.out.println(getURLEncoderString(str));
        System.out.println(getURLDecoderString(str));
        
    }

}

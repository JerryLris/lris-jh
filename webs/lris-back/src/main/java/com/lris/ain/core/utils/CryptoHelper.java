package com.lris.ain.core.utils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 密码工具类
 * */
public class CryptoHelper {
	private static final char[] HEXES = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static final String EMPTY_STRING = "";
	private static final byte[] ROW_BYTES = "80e36e39f34e678c".getBytes();

	public static String trim(String value) {
		return value == null ? null : value.trim();
	}

	public static boolean isEmpty(String value) {
		int length;
		if ((value == null) || ((length = value.length()) == 0)) {
			return true;
		}

		for (int index = 0; index < length; index++) {
			char ch = value.charAt(index);
			if ((ch != ' ') && (ch != ' ') && (!Character.isISOControl(ch))) {
				return false;
			}
		}
		return true;
	}
	
	public static String digest(String content) throws Exception {
		if (isEmpty(content)) {
			return content;
		}
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] ciphertext = digest.digest(content.getBytes());
		char[] chars = new char[ciphertext.length + ciphertext.length];
		int i = 0;
		for (byte element : ciphertext) {
			chars[(i++)] = HEXES[(element & 0xF)];
			chars[(i++)] = HEXES[(element >> 4 & 0xF)];
		}
		return new String(chars);
	}

	public static String encode(String content) throws Exception {
		if (isEmpty(content)) {
			return content;
		}
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec keySpec = new SecretKeySpec(ROW_BYTES, "AES");
		cipher.init(1, keySpec);
		byte[] ciphertext = cipher.doFinal(content.getBytes());
		return Base64.encodeBase64String(ciphertext);
	}

	public static String decode(String content)  {
		try {
			if (isEmpty(content)) {
				return content;
			}
			Cipher cipher = Cipher.getInstance("AES");
			SecretKeySpec keySpec = new SecretKeySpec(ROW_BYTES, "AES");
			cipher.init(2, keySpec);
			byte[] ciphertext = cipher.doFinal(Base64.decodeBase64(content));
			return new String(ciphertext);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**字符串脱敏 
	 *  1234567890 > 12****7890
	 *  12ab34efg > 12***4efg
	 * @author wuweijie
	 * @param str
	 * @return
	 */
	public static String strToAsterisk(String str) {
		if(StringUtils.isNotBlank(str)) {
			int strlen = str.length();
			String encodeNumbber = "";
			for(int i=0;i < strlen-6;i++) {
				encodeNumbber += "*";
			}
			StringBuilder sb = new StringBuilder(str);
			sb.replace(2, strlen-4, encodeNumbber);
			str = sb.toString();
		}
		return str;
	}

	/**电子邮箱脱敏 
	 *  12323aaa@abc.com > 12**3aaa@abc.com
	 * @author wuweijie
	 * @param str
	 * @return
	 */
	public static String emailToAsterisk(String str){
		if(StringUtils.isNotBlank(str)) {
			String prefix = str.split("@")[0];
			int prefixLen = prefix.length();
			int asteriskLen = prefixLen - 6;
			int startLen = 2;
			int lastLen = prefixLen - 4;
			if(prefixLen < 7) {
				prefixLen = prefixLen-2;
				startLen = 1;
				lastLen = prefixLen - 1;
			}
			String encodeNumbber = "";
			for(int i=0;i < asteriskLen;i++) {
				encodeNumbber += "*";
			}
			StringBuilder sb = new StringBuilder(prefix);
			sb.replace(startLen, lastLen, encodeNumbber);
			str = sb.toString() + str.split("@")[1];
		}
		return str;
	}

}

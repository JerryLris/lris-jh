package com.lris.ain.core.utils;

import java.awt.image.BufferedImage;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

public class KaptchaUtils {

	private static Producer producer = null;
	
	static{
		ImageIO.setUseCache(false);
		final Properties props = new Properties();
		props.setProperty("kaptcha.image.height", "70");
		props.put("kaptcha.border", "no");
		props.put("kaptcha.textproducer.font.color", "black");
		props.put("kaptcha.textproducer.char.space", "5");
		producer = new Config(props).getProducerImpl();
	}
	
	public static BufferedImage genCode(String code){
		BufferedImage bi = producer.createImage(code);
		return bi;
	}
}

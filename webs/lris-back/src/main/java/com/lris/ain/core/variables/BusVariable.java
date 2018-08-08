package com.lris.ain.core.variables;

import java.io.InputStreamReader;

@VariableTypeAnnotation(id = "BUS", name = "业务相关")
public enum BusVariable implements VariableBean{
	
	/**
	 * 
	 */
	WHO_ME_I("这是谁"){
		
		@Override
        public String getValue()
        {
            return "lris";
        }
	};
	 

	BusVariable(String description) {
		StringBuilder builder = new StringBuilder(getType());
		builder.append('.').append(name());
		key = builder.toString();
		this.description = description;
	}
	
	protected final String key;
	protected final String description;
	
	@Override
	public String getType() {
		return BusVariable.class.getAnnotation(VariableTypeAnnotation.class)
				.id();
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		try (InputStreamReader reader = new InputStreamReader(
				BusVariable.class.getResourceAsStream(getKey()), "UTF-8")) {
			StringBuilder builder = new StringBuilder();
			char[] cbuf = new char[1024];
			int len = reader.read(cbuf);
			while (len > 0) {
				builder.append(cbuf, 0, len);
				len = reader.read(cbuf);
			}
			return builder.toString();
		} catch (Throwable t) {
		}
		return null;
	}

	@Override
	public String getDescription() {
		return description;
	}

}

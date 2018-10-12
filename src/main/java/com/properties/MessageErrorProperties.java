package com.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class MessageErrorProperties {
	
	@Autowired
	private Environment env;
	
	
	public String getMessageError(String key) {
		return env.getProperty(key);
	}
}

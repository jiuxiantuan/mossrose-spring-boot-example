package com.jiuxian.mossrose.springboot.example.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BusinessBean implements IBusinessBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessBean.class);

	@Override
	public String echo(String value) {
		LOGGER.info(this.toString());
		return "Capital " + value;
	}

}

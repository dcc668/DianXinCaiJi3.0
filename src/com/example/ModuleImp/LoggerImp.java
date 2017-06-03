package com.example.ModuleImp;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;

public class LoggerImp implements Logger,ConfigurationAWare{
	
	static {
		PropertyConfigurator.configure("src/log4j.properties");
	}
	org.apache.log4j.Logger logger=org.apache.log4j.Logger.getRootLogger();
	
	@Override
	public void init(Properties pro) {
		
	}

	@Override
	public void setConfiguration(Configuration conf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String msg) {
			logger.debug(msg);
	}

	@Override
	public void info(String msg) {
		// TODO Auto-generated method stub
			logger.info(msg);
	}

	@Override
	public void warn(String msg) {
		// TODO Auto-generated method stub
			logger.warn(msg);
	}

	@Override
	public void error(String msg) {
		// TODO Auto-generated method stub
			logger.error(msg);
	}

	@Override
	public void fatal(String msg) {
		// TODO Auto-generated method stub
		logger.fatal(msg);
	}

}

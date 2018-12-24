package com.edu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	static Logger logger = LogManager.getLogger(Log.class.getName());
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss：SSS");
	static String nowtime = sdf.format(new Date());

	public static void fatal(String msg) {
		logger.info("---------------"+nowtime+"-------------");
		logger.fatal(msg);
	}

	public static void error(String msg) {
		logger.info("---------------"+nowtime+"-------------");
		logger.error(msg);
	}

	public static void warn(String msg) {
		logger.info("---------------"+nowtime+"-------------");
		logger.warn(msg);
	}

	public static void info(String msg) {
		logger.info("---------------"+nowtime+"-------------");
		logger.info(msg);
	}

	public static void debug(String msg) {
		logger.info("---------------"+nowtime+"-------------");
		logger.debug(msg);
	}
}


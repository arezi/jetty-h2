package com.arezi.jetty.h2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.jetty.util.log.Logger;

class LogJettyOutputStream extends OutputStream {
	
	enum Level {
		DEBUG, INFO, WARN;
	}

	private ByteArrayOutputStream bao = new ByteArrayOutputStream();
    
	private Logger log;

	private Level level;
	
	public LogJettyOutputStream(Logger log, Level level) {
		this.log = log;
		this.level = level;
	}
	
	
	public void write(int b) throws IOException {
		
		if( (char)b == '\n' ) {
			String msg = bao.toString();
			msg = msg.substring(0, msg.length() -1);
			printLog(msg);
	        bao = new ByteArrayOutputStream();
		} else {
			bao.write(b);
		}

	}


	private void printLog(String msg) {

		if (level == Level.DEBUG) {
			log.debug(msg);
		} else if (level == Level.INFO) {
			log.info(msg);
		} else if (level == Level.WARN) {
			log.warn(msg);
		}
		
	}
	
}

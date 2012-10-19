package com.arezi.jetty.h2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.h2.tools.Server;

import com.arezi.jetty.h2.LogJettyOutputStream.Level;


public class H2LifeCycle extends AbstractLifeCycle {

	private Server h2;
	private Logger log;
	
	private String baseDir;
	private boolean ifExists;
	private boolean tcpAllowOthers;
	private Integer tcpPort;
	private boolean web;
	private Integer webPort;

	
	public H2LifeCycle() {
		log = Log.getLogger(this.getClass());
	}
	
	@Override
	protected void doStart() throws Exception {
		if (h2 != null) {
			return;
		} 
		
		List<String> lstArgs = new ArrayList<String>();
		lstArgs.add("-tcp");
		
		if (tcpPort != null) {
			lstArgs.add("-tcpPort");
			lstArgs.add(tcpPort.toString());
		}

		if (tcpAllowOthers) {
			lstArgs.add("-tcpAllowOthers");
		}
		
		if (baseDir != null) {
			lstArgs.add("-baseDir");
			lstArgs.add(baseDir);
		}
		
		if (ifExists) {
			lstArgs.add("-ifExists");
		}
		

		if (web) {
			lstArgs.add("-web");
			if (webPort != null) {
				lstArgs.add("-webPort");
				lstArgs.add(webPort.toString());
			}
		}
		
		log.info(STARTING, "Starting H2 database.");

		h2 = new Server();
		
		h2.setOut(new PrintStream(new LogJettyOutputStream(log, RUNNING, Level.INFO)));
        
		h2.runTool(lstArgs.toArray(new String[lstArgs.size()]));

		log.info(STARTED, "H2 database started.");

	}
	
	@Override
	protected void doStop() throws Exception {
		log.info(STARTING, "Stopping H2 database.");
		h2.shutdown();
		log.info(STOPPED, "H2 database stopped.");
	}

	
	
	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public boolean isIfExists() {
		return ifExists;
	}

	public void setIfExists(boolean ifExists) {
		this.ifExists = ifExists;
	}

	public boolean isTcpAllowOthers() {
		return tcpAllowOthers;
	}

	public void setTcpAllowOthers(boolean tcpAllowOthers) {
		this.tcpAllowOthers = tcpAllowOthers;
	}

	public Integer getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(Integer tcpPort) {
		this.tcpPort = tcpPort;
	}

	public boolean isWeb() {
		return web;
	}

	public void setWeb(boolean web) {
		this.web = web;
	}

	public Integer getWebPort() {
		return webPort;
	}

	public void setWebPort(Integer webPort) {
		this.webPort = webPort;
	}

}

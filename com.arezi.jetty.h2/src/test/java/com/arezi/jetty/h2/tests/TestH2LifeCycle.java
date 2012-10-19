package com.arezi.jetty.h2.tests;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Assert;
import org.junit.Test;

import com.arezi.jetty.h2.H2LifeCycle;


public class TestH2LifeCycle {

	
	@Test
	public void teste2() throws Exception {
		H2LifeCycle lc = new H2LifeCycle();
		
		lc.setBaseDir(System.getProperty("os.name").startsWith("Windows") ?
				"c:\\temp" : "/tmp");
		lc.setWeb(true);
		lc.setWebPort(15678);
		
		lc.start();

		Thread.sleep(1000);
		
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:test", "sa", "");
		
		Assert.assertNotNull(conn);
		
		conn.close();
		
		lc.stop();


		try {
			conn = null;
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:test", "sa", "");
			Assert.fail("Should not have connected!");
		} catch(Exception e) {
			// OK
		}
		
		Assert.assertNull(conn);
		
	}
}

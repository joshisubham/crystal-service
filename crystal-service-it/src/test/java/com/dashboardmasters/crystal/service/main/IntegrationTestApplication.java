package com.dashboardmasters.crystal.service.main;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.dashboardmasters.crystal.service.main.Application;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= {com.dashboardmasters.crystal.service.main.Application.class})
public class IntegrationTestApplication {
	
	@LocalServerPort
	protected int port;
	
	private static Logger LOG = LoggerFactory.getLogger(IntegrationTestApplication.class);
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				LOG.info("ServletContext Initialized");
			}
			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				LOG.info("ServletContext Destroyed");
			}
		};
	}
	@Bean(name="applicationVersion")
	public String applicationVersion() {
		String version = getClass().getPackage().getImplementationVersion();
		return version == null ? "unknown" : version;
	}
}

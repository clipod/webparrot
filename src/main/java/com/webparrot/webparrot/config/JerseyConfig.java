package com.webparrot.webparrot.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.webparrot.webparrot.resources.WebParrotResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(WebParrotResource.class);
	}
}

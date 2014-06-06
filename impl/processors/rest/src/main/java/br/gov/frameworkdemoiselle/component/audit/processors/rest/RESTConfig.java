package br.gov.frameworkdemoiselle.component.audit.processors.rest;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix = "frameworkdemoiselle.audit.processor.rest")
public class RESTConfig {

	@Name("server.url")
	private String serverUrl;
	
	public String getServerUrl() {
		return serverUrl;
	}
	
	

}

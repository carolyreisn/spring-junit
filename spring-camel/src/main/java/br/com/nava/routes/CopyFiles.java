package br.com.nava.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CopyFiles extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:input").to("file:output");
		
	}

}

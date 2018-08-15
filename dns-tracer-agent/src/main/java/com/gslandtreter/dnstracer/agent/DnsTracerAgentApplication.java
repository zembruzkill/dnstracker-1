package com.gslandtreter.dnstracer.agent;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DnsTracerAgentApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DnsTracerAgentApplication.class).web(WebApplicationType.NONE).run(args);
    }

}

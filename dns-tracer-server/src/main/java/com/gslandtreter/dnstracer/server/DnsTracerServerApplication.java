package com.gslandtreter.dnstracer.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.gslandtreter.dnstracer")

public class DnsTracerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DnsTracerServerApplication.class, args);
    }
}

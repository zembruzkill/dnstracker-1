package com.gslandtreter.dnstracer.agent;

import com.gslandtreter.dnstracer.agent.asn.ASNDatabase;
import com.gslandtreter.dnstracer.agent.dao.util.DomainDnssFactory;
import com.gslandtreter.dnstracer.agent.job.VersionInfoHandler;
import com.gslandtreter.dnstracer.agent.rest.DnsTracerService;
import com.gslandtreter.dnstracer.agent.rest.DnsTracerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@Configuration
public class AppConfig {

    @Value("${as.databaseFile}")
    private String asnDatabaseFile;

    @Value("${as.namesDatabaseFile}")
    private String asNamesDatabaseFile;

    @Value("${threadPool.size}")
    private Integer threadPoolSize;

    @Value("${node.id}")
    private String nodeId;

    @Bean
    public DomainDnssFactory domainDnss() {
        return new DomainDnssFactory();
    }

    @Bean
    public ASNDatabase asnDatabase() throws IOException {
        return new ASNDatabase(new File(asnDatabaseFile), new File(asNamesDatabaseFile));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DnsTracerService dnsTracerService() {
        return new DnsTracerServiceImpl();
    }

    @Bean
    public VersionInfoHandler versionInfoHandler() {
        return new VersionInfoHandler(dnsTracerService, nodeId);
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setThreadNamePrefix("hopParser-");
        executor.setCorePoolSize(threadPoolSize);
        executor.setMaxPoolSize(threadPoolSize);
        executor.setQueueCapacity(1000000);

        return executor;
    }

    @Autowired
    private DnsTracerService dnsTracerService;
}

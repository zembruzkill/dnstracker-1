package com.gslandtreter.dnstracer.server;

import com.gslandtreter.dnstracer.server.repository.StatisticsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public StatisticsRepository statisticsRepository() {
        return new StatisticsRepository();
    }
}

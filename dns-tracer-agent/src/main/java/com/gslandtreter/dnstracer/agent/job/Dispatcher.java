package com.gslandtreter.dnstracer.agent.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
public class Dispatcher implements CommandLineRunner {

    @Value("${domainList.file}")
    private String domainFile;

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void run(String... args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(domainFile)))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] splitLine = line.split(",");

                HopAnalyser job = ctx.getBean(HopAnalyser.class);
                job.setDomainName(splitLine[1]);
                job.setPosition( Integer.parseInt(splitLine[0]));
                taskExecutor.execute(job);
            }
        }
    }
}

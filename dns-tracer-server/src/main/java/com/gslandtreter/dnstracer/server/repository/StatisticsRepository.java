package com.gslandtreter.dnstracer.server.repository;

import com.gslandtreter.dnstracer.common.beam.ExecutionStatistics;
import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class StatisticsRepository {

    @Autowired
    VersionInfoRepository versionInfoRepository;

    @Autowired
    DomainDnssRepository domainDnssRepository;

    private List<ExecutionStatistics> executionStatistics;
    private boolean initialized = false;

    public StatisticsRepository() {
        executionStatistics = new ArrayList<>();
    }

    private void initialize() {
        List<VersionInfoEntity> runningExecutions = versionInfoRepository.getRunningExecutions();

        for(VersionInfoEntity runningExecution : runningExecutions) {
            ExecutionStatistics runningJobStatistics = new ExecutionStatistics();
            runningJobStatistics.setVersionInfo(runningExecution);

            executionStatistics.add(runningJobStatistics);
        }

        initialized = true;
    }

    private void refreshStatistics() {
        if(!initialized) {
            initialize();
        }

        for (ExecutionStatistics statistics: executionStatistics) {
            VersionInfoEntity versionInfo = statistics.getVersionInfo();

            long processedDomains = domainDnssRepository.getProcessedDomainCount(versionInfo.getId());
            statistics.setTotalDomainsProcessed(processedDomains);

            long totalEntries = domainDnssRepository.getTotalDnsEntries(versionInfo.getId());
            statistics.setTotalDnsEntries(totalEntries);
        }
    }

    public ExecutionStatistics onNewExecutionStarted(VersionInfoEntity versionInfoEntity) {

        if(!initialized) {
            initialize();
        }

        ExecutionStatistics runningJobStatistics = new ExecutionStatistics();
        runningJobStatistics.setVersionInfo(versionInfoEntity);

        executionStatistics.add(runningJobStatistics);
        return runningJobStatistics;
    }

    public void onExecutionFinished(VersionInfoEntity versionInfoEntity) {

        if(!initialized) {
            initialize();
        }

        ExecutionStatistics runningExecution = executionStatistics.stream()
                .filter(i -> i.getVersionInfo().getId() == versionInfoEntity.getId()).findFirst().orElse(null);

        executionStatistics.remove(runningExecution);
    }

    public List<ExecutionStatistics> getExecutionStatistics() {
        refreshStatistics();
        return executionStatistics;
    }
}

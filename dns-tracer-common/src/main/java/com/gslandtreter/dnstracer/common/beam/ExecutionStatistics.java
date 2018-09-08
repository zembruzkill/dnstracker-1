package com.gslandtreter.dnstracer.common.beam;

import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;

public class ExecutionStatistics {

    private VersionInfoEntity versionInfo;
    private long totalDomainsProcessed = 0;
    private long totalDnsEntries = 0;

    public VersionInfoEntity getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfoEntity versionInfo) {
        this.versionInfo = versionInfo;
    }

    public long getTotalDomainsProcessed() {
        return totalDomainsProcessed;
    }

    public void setTotalDomainsProcessed(long totalDomainsProcessed) {
        this.totalDomainsProcessed = totalDomainsProcessed;
    }

    public long getTotalDnsEntries() {
        return totalDnsEntries;
    }

    public void setTotalDnsEntries(long totalDnsEntries) {
        this.totalDnsEntries = totalDnsEntries;
    }
}

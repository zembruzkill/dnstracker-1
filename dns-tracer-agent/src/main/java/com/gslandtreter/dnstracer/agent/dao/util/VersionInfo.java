package com.gslandtreter.dnstracer.agent.dao.util;

import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class VersionInfo {

    private static VersionInfoEntity instance = null;

    public synchronized static VersionInfoEntity getVersionInfo() {
        if (instance == null) {
            instance = generateNewVersionInfo();
        }
        return instance;
    }

    public synchronized static void finished() {
        VersionInfoEntity versionInfoEntity = getVersionInfo();
        versionInfoEntity.setEndDate(Timestamp.from(Instant.now()));

    }

    private static VersionInfoEntity generateNewVersionInfo() {
        VersionInfoEntity versionInfoEntity = new VersionInfoEntity();
        versionInfoEntity.setStartDate(Timestamp.from(Instant.now()));
        versionInfoEntity.setId(1);


        return versionInfoEntity;
    }
}

package com.gslandtreter.dnstracer.server.controller;

import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;
import com.gslandtreter.dnstracer.server.repository.VersionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api")
public class VersionInfoController {

    @Autowired
    VersionInfoRepository versionInfoRepository;

    @GetMapping("/versionInfo")
    public VersionInfoEntity getDnssEntityByVersion(@RequestParam(value = "region") String region,
                                                    HttpServletRequest request) {

        VersionInfoEntity versionInfoEntity = versionInfoRepository.findUnfinishedVersion(region);

        // Unfinished execution found. Return it.
        if(versionInfoEntity != null)
            return versionInfoEntity;

        // Create new execution
        versionInfoEntity = new VersionInfoEntity();
        versionInfoEntity.setRegion(region);
        versionInfoEntity.setWorkerIp(request.getRemoteAddr());
        versionInfoEntity.setStartDate(new Timestamp(System.currentTimeMillis()));

        return versionInfoRepository.save(versionInfoEntity);
    }

}

package com.gslandtreter.dnstracer.server.controller;

import com.gslandtreter.dnstracer.common.beam.ExecutionStatistics;
import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;
import com.gslandtreter.dnstracer.common.entity.DomainDnssPKEntity;
import com.gslandtreter.dnstracer.server.repository.DomainDnssRepository;
import com.gslandtreter.dnstracer.server.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticsController {

    @Autowired
    StatisticsRepository statisticsRepository;

    @GetMapping("/statistics")
    public List<ExecutionStatistics> getStatistics() {
        return statisticsRepository.getExecutionStatistics();
    }
}

package com.gslandtreter.dnstracer.server.controller;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;
import com.gslandtreter.dnstracer.common.entity.DomainDnssPKEntity;
import com.gslandtreter.dnstracer.server.repository.DomainDnssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DomainDnssController {

    @Autowired
    DomainDnssRepository domainDnssRepository;

    @GetMapping("/dnsTraceEntry")
    public DomainDnssEntity getDnssEntityByVersion(@RequestParam(value = "domain") String domain,
                                                   @RequestParam(value = "ns_name") String nsName,
                                                   @RequestParam(value = "trace_version") int traceVersion) {

        DomainDnssPKEntity privateKey = new DomainDnssPKEntity();
        privateKey.setDomain(domain);
        privateKey.setNsName(nsName);
        privateKey.setTraceVersion(traceVersion);

        return domainDnssRepository.findById(privateKey).orElse(null);
    }

    @PostMapping("/dnsTraceEntries")
    public List<DomainDnssEntity> createDnssEntity(@Valid @RequestBody Iterable<DomainDnssEntity> entities) {
        return domainDnssRepository.saveAll(entities);
    }

    @GetMapping("/processedDomains")
    public List<String> getProcessedDomains(@RequestParam(value = "trace_version") Integer traceVersion) {
        return domainDnssRepository.getAlreadyProcessedDomains(traceVersion);
    }

}

package com.gslandtreter.dnstracer.agent.rest;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class DnsTracerServiceImpl implements DnsTracerService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${rest.server.endpoint}")
    String endpoint;

    @Override
    public List<DomainDnssEntity> insertDnsServerEntries(Iterable<DomainDnssEntity> entries) {
        DomainDnssEntity[] response = restTemplate.postForObject(endpoint + "/dnsTraceEntries", entries, DomainDnssEntity[].class);
        return Arrays.asList(response);
    }

}

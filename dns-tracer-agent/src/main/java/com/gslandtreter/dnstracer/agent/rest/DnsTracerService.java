package com.gslandtreter.dnstracer.agent.rest;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;

import java.util.List;

public interface DnsTracerService {
    List<DomainDnssEntity> insertDnsServerEntries(Iterable<DomainDnssEntity> entries);
}

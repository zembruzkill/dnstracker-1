package com.gslandtreter.dnstracer.agent.dao.util;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;

public class DomainDnssFactory {

    public static DomainDnssEntity getDomainDnssEntity(
            Integer position,
            String domain,
            String nameServer,
            String nsIPv4,
            String nsIPv6,
            String hbtl,
            Integer hbtlAs,
            String hbtlAsSubnet,
            Integer nsAsn,
            String nsSubnet,
            Integer traceVersion
    ) {
        DomainDnssEntity entity = new DomainDnssEntity();

        entity.setPosition(position);
        entity.setDomain(domain);
        entity.setNsName(nameServer);
        entity.setNsIp(nsIPv4);
        entity.setNsIpv6(nsIPv6);
        entity.setHbtlIp(hbtl);
        entity.setHbtlName(hbtl);
        entity.setHbtlAsn(hbtlAs);
        entity.setHbtlSubnet(hbtlAsSubnet);
        entity.setNsAsn(nsAsn);
        entity.setNsSubnet(nsSubnet);
        entity.setTraceVersion(traceVersion);

        return entity;
    }

}

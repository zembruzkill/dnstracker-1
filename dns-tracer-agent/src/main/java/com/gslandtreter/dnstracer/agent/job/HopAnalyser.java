package com.gslandtreter.dnstracer.agent.job;

import com.gslandtreter.dnstracer.agent.asn.ASEntry;
import com.gslandtreter.dnstracer.agent.asn.ASNDatabase;
import com.gslandtreter.dnstracer.agent.dao.util.DomainDnssFactory;
import com.gslandtreter.dnstracer.agent.networkUtils.NSResolver;
import com.gslandtreter.dnstracer.agent.networkUtils.TRFetch;
import com.gslandtreter.dnstracer.agent.rest.DnsTracerService;
import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class HopAnalyser implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();

    private String domainName;
    private int position;
    private List<String> nameServers;

    @Autowired
    private ASNDatabase asnDatabase;

    @Autowired
    DnsTracerService dnsTracerService;

    @Autowired
    VersionInfoHandler versionInfoHandler;

    private static InetAddress getHopBeforeTheLast(List<InetAddress> hops) {
        InetAddress hopBeforeTheLast = null;
        final int totalHops = hops.size();

        if (totalHops > 2 && hops.get(totalHops - 2) != null) {
            hopBeforeTheLast = hops.get(totalHops - 2);
        }
        return hopBeforeTheLast;
    }

    private DomainDnssEntity processHopInfo(String nameServer, List<InetAddress> hops) {
        ASEntry hbtlAs;
        String hbtlAsName;
        InetAddress hopBeforeTheLast;

        InetAddress nsIpv6 = NSResolver.getIPv6Address(nameServer);
        InetAddress nsIPv4 = NSResolver.getIPv4Address(nameServer);

        if (nsIPv4 == null) {
            return DomainDnssFactory.getDomainDnssEntity(
                    position,
                    domainName,
                    nameServer,
                    null,
                    null,
                    null,
                    -1,
                    null,
                    -1,
                    null,
                    versionInfoHandler.getVersionInfoEntity().getId());
        }

        ASEntry nsAsEntry = asnDatabase.getASEntry(nsIPv4.getHostAddress());

        hopBeforeTheLast = getHopBeforeTheLast(hops);

        if (hopBeforeTheLast != null && hbtlAs) {
            hbtlAs = asnDatabase.getASEntry(hopBeforeTheLast.getHostAddress());
            hbtlAsName = asnDatabase.getAsName(hbtlAs);

            LOGGER.debug("[({}){}/{}] - HBTL: {} - [{}]{}",
                    domainName, nameServer, nsIpv6 != null ? nsIpv6.getHostAddress() : null, hopBeforeTheLast.getHostAddress(), hbtlAs, hbtlAsName);

            DomainDnssEntity entity = DomainDnssFactory.getDomainDnssEntity(
                    position,
                    domainName,
                    nameServer,
                    nsIPv4.getHostAddress(),
                    nsIpv6 != null ? nsIpv6.getHostAddress() : null,
                    hopBeforeTheLast.getHostAddress(),
                    (int) hbtlAs.getAsNumber(),
                    hbtlAs.getAsSubnet(),
                    (int) nsAsEntry.getAsNumber(),
                    nsAsEntry.getAsSubnet(),
                    versionInfoHandler.getVersionInfoEntity().getId());

            return entity;
        } else {
            LOGGER.debug("[({}){}/{}] - HBTL: null - [-1]",
                    domainName, nameServer, nsIpv6 != null ? nsIpv6.getHostAddress() : null);

            DomainDnssEntity entity = DomainDnssFactory.getDomainDnssEntity(
                    position,
                    domainName,
                    nameServer,
                    nsIPv4.getHostAddress(),
                    nsIpv6 != null ? nsIpv6.getHostAddress() : null,
                    null,
                    -1,
                    null,
                    (int) nsAsEntry.getAsNumber(),
                    nsAsEntry.getAsSubnet(),
                    versionInfoHandler.getVersionInfoEntity().getId());

            return entity;
        }
    }

    @Override
    public void run() {
        try {
            nameServers = NSResolver.getDomainNS(domainName);
            List<DomainDnssEntity> newEntries = new ArrayList<>();

            if (nameServers == null) {
                LOGGER.debug("{} has invalid domain info.", domainName);
            }
            else {
                for (String nameServer : nameServers) {
                    List<InetAddress> hops = TRFetch.getHopsTo(nameServer);

                    if(hops == null) {
                        LOGGER.debug("{} ({}) has invalid hop info.", domainName, nameServer);
                        continue;
                    }

                    DomainDnssEntity entity = processHopInfo(nameServer, hops);
                    newEntries.add(entity);
                }
            }

            if(newEntries.size() == 0) {
                // Default entry! Fill domain with invalid info if nothing was found!
                DomainDnssEntity entity = DomainDnssFactory.getDomainDnssEntity(
                        position,
                        domainName,
                        null,
                        null,
                        null,
                        null,
                        -1,
                        null,
                        -1,
                        null,
                        versionInfoHandler.getVersionInfoEntity().getId());

                newEntries.add(entity);
            }

            dnsTracerService.insertDnsServerEntries(newEntries);

        } catch (Exception e) {
            LOGGER.error("Erro ao processar", e);
        }
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

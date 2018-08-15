package com.gslandtreter.dnstracer.common.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(DomainDnssPKEntity.class)
@Table(name = "domain_dnss")
public class DomainDnssEntity {

    private String domain;
    private Integer traceVersion;
    private String nsName;

    private Integer position;
    private String nsIp;
    private String hbtlName;
    private String hbtlIp;
    private Integer hbtlAsn;
    private String hbtlSubnet;
    private String nsIpv6;
    private Integer nsAsn;
    private String nsSubnet;

    @Id
    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Id
    @Column(name = "ns_name")
    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    @Basic
    @Column(name = "ns_ip")
    public String getNsIp() {
        return nsIp;
    }

    public void setNsIp(String nsIp) {
        this.nsIp = nsIp;
    }

    @Basic
    @Column(name = "hbtl_name")
    public String getHbtlName() {
        return hbtlName;
    }

    public void setHbtlName(String hbtlName) {
        this.hbtlName = hbtlName;
    }

    @Basic
    @Column(name = "hbtl_ip")
    public String getHbtlIp() {
        return hbtlIp;
    }

    public void setHbtlIp(String hbtlIp) {
        this.hbtlIp = hbtlIp;
    }

    @Basic
    @Column(name = "hbtl_asn")
    public Integer getHbtlAsn() {
        return hbtlAsn;
    }

    public void setHbtlAsn(Integer hbtlAsn) {
        this.hbtlAsn = hbtlAsn;
    }

    @Basic
    @Column(name = "hbtl_subnet")
    public String getHbtlSubnet() {
        return hbtlSubnet;
    }

    public void setHbtlSubnet(String hbtlSubnet) {
        this.hbtlSubnet = hbtlSubnet;
    }

    @Basic
    @Column(name = "ns_ipv6")
    public String getNsIpv6() {
        return nsIpv6;
    }

    public void setNsIpv6(String nsIpv6) {
        this.nsIpv6 = nsIpv6;
    }

    @Basic
    @Column(name = "ns_asn")
    public Integer getNsAsn() {
        return nsAsn;
    }

    public void setNsAsn(Integer nsAsn) {
        this.nsAsn = nsAsn;
    }

    @Basic
    @Column(name = "ns_subnet")
    public String getNsSubnet() {
        return nsSubnet;
    }

    public void setNsSubnet(String nsSubnet) {
        this.nsSubnet = nsSubnet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainDnssEntity that = (DomainDnssEntity) o;
        return Objects.equals(domain, that.domain) &&
                Objects.equals(position, that.position) &&
                Objects.equals(nsName, that.nsName) &&
                Objects.equals(nsIp, that.nsIp) &&
                Objects.equals(hbtlName, that.hbtlName) &&
                Objects.equals(hbtlIp, that.hbtlIp) &&
                Objects.equals(hbtlAsn, that.hbtlAsn) &&
                Objects.equals(hbtlSubnet, that.hbtlSubnet) &&
                Objects.equals(nsIpv6, that.nsIpv6) &&
                Objects.equals(nsAsn, that.nsAsn) &&
                Objects.equals(nsSubnet, that.nsSubnet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(domain, position, nsName, nsIp, hbtlName, hbtlIp, hbtlAsn, hbtlSubnet, nsIpv6, nsAsn, nsSubnet);
    }

    @Id
    @Column(name = "trace_version")
    public Integer getTraceVersion() {
        return traceVersion;
    }

    public void setTraceVersion(Integer traceVersion) {
        this.traceVersion = traceVersion;
    }
}

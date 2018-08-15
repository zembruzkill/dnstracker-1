package com.gslandtreter.dnstracer.common.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "version_info")
public class VersionInfoEntity {
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;
    private String region;
    private String workerIp;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "worker_ip")
    public String getWorkerIp() {
        return workerIp;
    }

    public void setWorkerIp(String workerIp) {
        this.workerIp = workerIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionInfoEntity that = (VersionInfoEntity) o;
        return id == that.id &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(workerIp, that.workerIp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, startDate, endDate, workerIp);
    }
}

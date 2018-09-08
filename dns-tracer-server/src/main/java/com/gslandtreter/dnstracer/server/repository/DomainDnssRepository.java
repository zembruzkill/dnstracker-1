package com.gslandtreter.dnstracer.server.repository;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;
import com.gslandtreter.dnstracer.common.entity.DomainDnssPKEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomainDnssRepository extends JpaRepository<DomainDnssEntity, DomainDnssPKEntity> {

    @Query("SELECT distinct v.domain FROM DomainDnssEntity v WHERE traceVersion = ?1")
    List<String> getAlreadyProcessedDomains(Integer traceVersion);

    @Query("SELECT count(distinct v.domain) FROM DomainDnssEntity v WHERE traceVersion = ?1")
    Long getProcessedDomainCount(Integer traceVersion);

    @Query("SELECT count(1) FROM DomainDnssEntity v WHERE traceVersion = ?1")
    Long getTotalDnsEntries(Integer traceVersion);
}

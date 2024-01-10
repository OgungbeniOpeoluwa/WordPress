package org.example.data.repository;

import org.example.data.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site,Long> {
    Site findByDomainName(String domainName);
}

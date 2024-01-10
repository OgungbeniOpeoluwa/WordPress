package org.example.services;

import org.example.data.model.UserRole;
import org.example.dto.WriteRequest;

public interface SiteService {
    void createSite(Long id, String domainName);

    void unlockSite(String domainName);

    void write(UserRole role, Long id, String fullname, WriteRequest request);
}

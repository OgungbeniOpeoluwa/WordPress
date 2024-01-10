package org.example.services;

import org.example.data.model.Site;
import org.example.data.model.UserRole;
import org.example.data.repository.SiteRepository;
import org.example.dto.WriteRequest;
import org.example.exception.SiteExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl implements SiteService{
    @Autowired
    SiteRepository siteRepository;
    @Override
    public void createSite(Long id, String domainName) {
        if(siteExist(domainName))throw new SiteExistException("site already exist");
        Site newSite = new Site();
        newSite.setDomainName(domainName);
        newSite.setUserId(id);
        siteRepository.save(newSite);
    }

    @Override
    public void unlockSite(String domainName) {
        if(!siteExist(domainName)) throw  new SiteExistException("Invalid domain name");
        Site site = siteRepository.findByDomainName(domainName);
        site.setLocked(false);
        siteRepository.save(site);
    }

    @Override
    public void write(UserRole role, Long id, String fullname, WriteRequest request) {
        if(!siteExist(request.getDomainName()))throw new SiteExistException("Invalid domain name");


    }

    private boolean siteExist(String domainName){
        Site site = siteRepository.findByDomainName(domainName);
        return site != null;
    }
}

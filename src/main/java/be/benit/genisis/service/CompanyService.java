package be.benit.genisis.service;

import be.benit.genisis.model.Company;
import be.benit.genisis.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    List<Company> findAll();

    Company save(Company newCompany);

    boolean update(Company newCompany, Long companyId);

    boolean addContactCompany(Long contactId,Long companyId);

}

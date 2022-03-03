package be.benit.genisis.service.impl;

import be.benit.genisis.model.Company;
import be.benit.genisis.model.Contact;
import be.benit.genisis.repositories.CompanyRepository;
import be.benit.genisis.service.CompanyService;
import be.benit.genisis.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final ContactService contactService;
    private final CompanyRepository companyRepository;


    public CompanyServiceImpl(CompanyRepository companyRepository,ContactService contactService){
        this.contactService=contactService;
        this.companyRepository=companyRepository;
    }


    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company save(Company newCompany) {
        newCompany.getContacts().forEach((contact -> {
            contact.setId(null);
            contactService.save(contact);
        }));
       return companyRepository.save(newCompany);
    }

    @Override
    public boolean update(Company newCompany, Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(companyOptional.isPresent()){
            newCompany.setId(companyId);
            newCompany.getContacts().forEach((contactService::save));
            companyRepository.save(newCompany);
            return true;
        }else {
            return false;
        }
    }


    @Override
    public boolean addContactCompany(Long contactId, Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        Optional<Contact> contactOptional = contactService.findById(contactId);
        if(companyOptional.isPresent() && contactOptional.isPresent()){
           Company company = companyOptional.get();
           Contact contact = contactOptional.get();
           company.getContacts().add(contact);
           companyRepository.save(company);
            return true;
        }else {
            return false;
        }
    }
}

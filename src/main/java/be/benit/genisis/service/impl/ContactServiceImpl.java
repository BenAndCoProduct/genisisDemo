package be.benit.genisis.service.impl;

import be.benit.genisis.model.Contact;
import be.benit.genisis.repositories.ContactRepository;
import be.benit.genisis.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository=contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public boolean update(Contact newContact,Long contactId) {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if(contactOptional.isPresent()){
            newContact.setId(contactId);
            contactRepository.save(newContact);
            return true;
        }else {
           return false;
        }
    }

    @Override
    public boolean delete(Long contactId) {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if(contactOptional.isPresent()){
            contactRepository.delete(contactOptional.get());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Optional<Contact> findById(Long contactId) {
        return contactRepository.findById(contactId);
    }
}

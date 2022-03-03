package be.benit.genisis.service;

import be.benit.genisis.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ContactService {

    List<Contact> findAll();
    Contact save(Contact contact);

    boolean update(Contact newContact,Long contactId);

    boolean delete(Long contactId);

    Optional<Contact> findById(Long companyId);

}

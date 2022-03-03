package be.benit.genisis.web;

import be.benit.genisis.model.Contact;
import be.benit.genisis.service.impl.ContactServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {
    private final ContactServiceImpl contactService;


    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<Contact> all() {
        return contactService.findAll();
    }

    @PostMapping(path = "contact", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> create(@Valid @RequestBody Contact newContact) {
        Contact contact = contactService.save(newContact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping(path = "contact/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> update(@PathVariable(value = "id") Long contactId,
                                          @Valid @RequestBody Contact newContact)  {
        if(contactService.update(newContact,contactId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found");
        }

    }


    @DeleteMapping(path = "contact/{id}")
    public ResponseEntity<Long> delete(@PathVariable (value = "id") Long contactId) {
        if(contactService.delete(contactId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found");
        }

    }



}

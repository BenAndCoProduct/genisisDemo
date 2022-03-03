package be.benit.genisis.repositories;

import be.benit.genisis.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {
}

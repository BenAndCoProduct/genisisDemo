package be.benit.genisis;

import be.benit.genisis.model.Address;
import be.benit.genisis.model.Contact;
import be.benit.genisis.repositories.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class InitDataBase {

    private static final Logger log = LoggerFactory.getLogger(InitDataBase.class);

    @Bean
    CommandLineRunner initDatabase(ContactRepository contactRepository) {

        return args -> {
            Address address = new Address("Belgique","Bruxelles","Rue du president","35");

            log.info("Preloading " + contactRepository.save(new Contact("Ben Abdelkader","Bilal",address,false,null)));
            log.info("Preloading " + contactRepository.save(new Contact()));
        };
    }
}
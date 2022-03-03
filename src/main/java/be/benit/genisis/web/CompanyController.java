package be.benit.genisis.web;

import be.benit.genisis.model.Company;
import be.benit.genisis.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<Company> all() {
        return companyService.findAll();
    }

    @PostMapping(path = "company",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> create(@Valid @RequestBody Company newCompany) {
        Company company = companyService.save(newCompany);
        return new ResponseEntity<>(company, HttpStatus.CREATED);

    }


    @PutMapping(path = "company/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> update(@PathVariable(value = "id") Long companyId,
                                          @Valid @RequestBody Company newCompany) {
        if(companyService.update(newCompany,companyId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found");
        }

    }

    @PutMapping(path = "company/{companyId}/contact/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> addContactCompany(@PathVariable(value = "companyId") Long companyId,
                                          @PathVariable(value = "contactId") Long contactId) {
        if(companyService.addContactCompany(contactId,companyId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found or contact Not Found ");
        }

    }


}

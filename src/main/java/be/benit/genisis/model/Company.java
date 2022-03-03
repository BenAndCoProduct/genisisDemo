package be.benit.genisis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends AbstractEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "company_country")),
            @AttributeOverride( name = "city", column = @Column(name = "company_city")),
            @AttributeOverride( name = "number", column = @Column(name = "company_number")),
            @AttributeOverride( name = "city", column = @Column(name = "company_city"))
    })
    private Address address;


    private String numberTva;

    @ManyToMany
    @JoinTable(name = "companies_contacts",
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"))
    private Set<Contact> contacts = new HashSet<>();
}

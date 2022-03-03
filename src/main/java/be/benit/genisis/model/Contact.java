package be.benit.genisis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contact extends AbstractEntity {


    private String name;

    private String firstName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "contact_country")),
            @AttributeOverride( name = "city", column = @Column(name = "contact_city")),
            @AttributeOverride( name = "number", column = @Column(name = "contact_number")),
            @AttributeOverride( name = "city", column = @Column(name = "contact_city"))
    })
    private Address address;

    private boolean freelance;

    @Nullable
    private String numberTva;

}

package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "country")
    @OrderBy("name")
    private Set<Customer> customers;
    @Version
    private long version;

    public Country(String name) {
        this.name = name;
        this.customers = new LinkedHashSet<>();
    }

    protected Country() {}

    public Set<Customer> getCustomers() {
        return Collections.unmodifiableSet(customers);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

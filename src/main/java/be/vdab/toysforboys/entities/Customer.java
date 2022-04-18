package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Embedded
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryId")
    private Country country;
    @OneToMany
    @JoinColumn(name = "customerId")
    private Set<Order> orders;
    @Version
    private long version;

    public Customer(String name, Address address, Country country) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.orders = new LinkedHashSet<>();
    }

    protected Customer(){}

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public boolean addOrder(Order order) {
        if (order == null) {
            throw new NullPointerException();
        }
        return orders.add(order);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Address getAddress() {
        return address;
    }
}
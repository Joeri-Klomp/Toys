package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "productlines")
public class Productline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "productline")
    @OrderBy("name")
    private Set<Product> products;
    @Version
    private long version;

    public Productline(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new LinkedHashSet<>();
    }

    protected Productline() {
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public boolean addProduct(Product product) {
        if (product == null) {
            throw new NullPointerException();
        }
        return products.add(product);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

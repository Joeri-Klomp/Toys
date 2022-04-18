package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date ordered;
    private Date required;
    private Date shipped;
    private String comments;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToMany(mappedBy = "orders")
    private Set<Product> products = new LinkedHashSet<>();
    @ElementCollection
    @CollectionTable(name = "orderdetails",
    joinColumns = @JoinColumn(name = "orderId"))
    private Set<OrderDetail> orderDetails;
    @Version
    private long version;

    public Order(Date ordered, Date required, Date shipped, String comments, Status status, Customer customer) {
        this.ordered = ordered;
        this.required = required;
        this.shipped = shipped;
        this.comments = comments;
        this.status = status;
        setCustomer(customer);
        this.orderDetails = new LinkedHashSet<>();
    }

    protected Order() {}

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Set<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableSet(orderDetails);
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new NullPointerException();
        }
        this.customer = customer;
    }

    public boolean addProduct(Product product) {
        var added = products.add(product);
        if (!product.getOrders().contains(this)) {
            product.addOrder(this);
        }
        return added;
    }

    public boolean removeProduct(Product product) {
        var removed = products.remove(product);
        if (product.getOrders().contains(this)) {
            product.removeOrder(this);
        }
        return removed;
    }

    public long getId() {
        return id;
    }

    public String getComments() {
        return comments;
    }

    public Status getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getOrdered() {
        return ordered;
    }

    public Date getRequired() {
        return required;
    }

    public Date getShipped() {
        return shipped;
    }
}

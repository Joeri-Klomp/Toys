package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String scale;
    private String description;
    private int inStock;
    private int inOrder;
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productlineId")
    private Productline productline;
    @ManyToMany
    @JoinTable(name = "orderdetails", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "orderId"))
    private Set<Order> orders = new LinkedHashSet<>();
    @ElementCollection
    @CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderId"))
    private Set<OrderDetail> orderDetails;
    @Version
    private long version;

    public Product(String name, String scale, String description, int inStock, int inOrder, BigDecimal price, Productline productLine) {
        this.name = name;
        this.scale = scale;
        this.description = description;
        this.inStock = inStock;
        this.inOrder = inOrder;
        this.price = price;
        this.productline = productLine;
        this.orderDetails = new LinkedHashSet<>();
    }

    protected Product() {
    }

    public Set<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableSet(orderDetails);
    }

    public boolean addOrder(Order order) {
        var added = orders.add(order);
        if (!order.getProducts().contains(this)) {
            order.addProduct(this);
        }
        return added;
    }

    public boolean removeOrder(Order order) {
        var removed = orders.remove(order);
        if (order.getProducts().contains(this)) {
            order.removeProduct(this);
        }
        return removed;
    }

    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScale() {
        return scale;
    }

    public String getDescription() {
        return description;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInOrder() {
        return inOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Productline getProductline() {
        return productline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return inStock == product.inStock && inOrder == product.inOrder && name.equals(product.name) && scale.equals(product.scale) && description.equals(product.description) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scale, description, inStock, inOrder, price);
    }
}

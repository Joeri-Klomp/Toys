package be.vdab.toysforboys.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@Access(AccessType.FIELD)
public class OrderDetail {
    private int ordered;
    private BigDecimal priceEach;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId")
    private Product product;

    public OrderDetail(int ordered, BigDecimal priceEach) {
        this.ordered = ordered;
        this.priceEach = priceEach;
    }

    protected OrderDetail() {}

    public int getOrdered() {
        return ordered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public Product getProduct() {
        return product;
    }
}

package be.vdab.toysforboys.repositories;

import be.vdab.toysforboys.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(long id);
    List<Order> findUnshipped();
}

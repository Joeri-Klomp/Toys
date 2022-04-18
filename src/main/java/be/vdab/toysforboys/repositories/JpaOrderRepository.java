package be.vdab.toysforboys.repositories;

import be.vdab.toysforboys.entities.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepository implements OrderRepository{
    private final EntityManager entityManager;

    public JpaOrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Order> findById(long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> findUnshipped() {
        String JPQLquery = "select d from Order d where d.status <> be.vdab.toysforboys.entities.Status.SHIPPED and d.status <> be.vdab.toysforboys.entities.Status.CANCELLED order by d.ordered";
        return entityManager.createQuery(JPQLquery, Order.class).getResultList();
    }


}

package be.vdab.toysforboys.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/insertTestData.sql")
@Import(JpaOrderRepository.class)
class JpaOrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaOrderRepository repository;

    JpaOrderRepositoryTest(JpaOrderRepository repository) {
        this.repository = repository;
    }

    private long id_testOrder() {
        return jdbcTemplate.queryForObject("select id from orders where comments = 'test'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(id_testOrder())).hasValueSatisfying(order -> assertThat(order.getComments()).isEqualTo("test"));
    }
}
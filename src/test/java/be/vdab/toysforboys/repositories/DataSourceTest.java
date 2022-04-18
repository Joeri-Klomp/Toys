package be.vdab.toysforboys.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class DataSourceTest {
    private final DataSource datasource;

    public DataSourceTest(DataSource datasource) {
        this.datasource = datasource;
    }

    @Test
    void getConnection() throws SQLException {
        try (var connection = datasource.getConnection()) {
            assertThat(connection.getCatalog()).isEqualTo("toysforboys");
        }
    }
}

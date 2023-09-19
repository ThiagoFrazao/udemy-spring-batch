package curso.udemy.springbatch.batch.reader;

import curso.udemy.springbatch.batch.dao.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class ClienteJdbcReader {

    @Bean
    public JdbcCursorItemReader<Cliente> gerarJdbcReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("clienteJdbcReader")
                .dataSource(dataSource)
                .sql("select * from Cliente")
                .rowMapper(new BeanPropertyRowMapper<>(Cliente.class))
                .build();

    }

}

package curso.udemy.springbatch.batch.writer;


import curso.udemy.springbatch.batch.dao.Cliente;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class ClienteJdbcWriter {

    @Bean
    public JdbcBatchItemWriter<Cliente> jdbcClienteWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Cliente>()
                .dataSource(dataSource)
                .sql("INSERT INTO Cliente (nome, sobrenome, idade, email) VALUES (?, ?, ?, ?)")
                .itemPreparedStatementSetter(prepararClienteParaInsersao())
                .build();

    }

    private ItemPreparedStatementSetter<Cliente> prepararClienteParaInsersao() {
        return new ItemPreparedStatementSetter<Cliente>() {
            @Override
            public void setValues(Cliente cliente, PreparedStatement ps) throws SQLException {
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getSobrenome());
                ps.setString(3, cliente.getIdade());
                ps.setString(4, cliente.getEmail());
            }
        };
    }


}

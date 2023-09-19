package curso.udemy.springbatch.batch.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CLIENTE")
@Data
public class Cliente {

    @Id
    @Column(name = "ID")
    private Long id;

    private String nome;

    private String sobrenome;

    private String idade;

    private String email;

}

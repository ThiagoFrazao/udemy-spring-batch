package curso.udemy.springbatch.batch.steps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class ImprimeNomeStep {


    @Bean
    public Step gerarSep1(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet tasklet) {
        log.info("Step encontrado");
        return new StepBuilder("step2", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

}

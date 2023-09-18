package curso.udemy.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class ImprimeNomeBatchConfig {

    @Bean
    public Job gerarJob1(JobRepository jobRepository, @Qualifier("gerarSep1") Step step) {
        log.info("Step: {}", step.getName());
        return new JobBuilder("job1", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step gerarSep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        log.info("Step encontrado");
        return new StepBuilder("step2", jobRepository)
                .tasklet(taskImprimirArg(null), transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet taskImprimirArg(@Value("#{jobParameters['nome']}") final String argNome) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("Nome arg recebido {}", argNome);
                return RepeatStatus.FINISHED;
            }
        };

    }


}

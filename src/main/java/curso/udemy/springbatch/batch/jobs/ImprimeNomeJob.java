package curso.udemy.springbatch.batch.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ImprimeNomeJob {

    @Bean
    public Job gerarJob1(JobRepository jobRepository, @Qualifier("gerarSep1") Step step) {
        log.info("Step: {}}", step.getName());
        return new JobBuilder("job1", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}

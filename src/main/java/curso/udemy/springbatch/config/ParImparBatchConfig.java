package curso.udemy.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

@Configuration
@Slf4j
public class ParImparBatchConfig {

    @Bean
    public Job jogParImpar(JobRepository jobRepository, @Qualifier("stepImprimirParImpar") Step step) {
        log.info("Step: {}", step.getName());
        return new JobBuilder("parImpar", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step stepImprimirParImpar(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        log.info("Step encontrado");
        return new StepBuilder("imprimirParImpar", jobRepository)
                .<Integer, String>chunk(2, transactionManager)
                .reader(contaAte10Reader())
                .processor(verificaParOuImparProcessor())
                .writer(imprimeResultadoWriter())
                .build();
    }

    private ItemWriter<String> imprimeResultadoWriter() {
        return itens -> itens.forEach(log::info);
    }

    private FunctionItemProcessor<Integer, String> verificaParOuImparProcessor() {
        return new FunctionItemProcessor<>(
                item -> item % 2 == 0 ? "Item %s e par".formatted(item) : "Item %s e impar".formatted(item));
    }

    private IteratorItemReader<Integer> contaAte10Reader() {
        return new IteratorItemReader<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }


}

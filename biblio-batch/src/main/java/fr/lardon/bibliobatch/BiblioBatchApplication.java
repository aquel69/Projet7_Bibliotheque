package fr.lardon.bibliobatch;

import fr.lardon.bibliobatch.tasklet.HelloWorld;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class BiblioBatchApplication {


	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Step helloWorldStep(){
		return stepBuilderFactory.get("step")
				.tasklet(new HelloWorld())
				.build();
	}

	@Bean
	public Job HelloWorldJob(){
		return jobBuilderFactory.get("job")
				.start(helloWorldStep())
				.build();
	}

	public static void main(String[] args) {

		SpringApplication.run(BiblioBatchApplication.class, args);
	}

}

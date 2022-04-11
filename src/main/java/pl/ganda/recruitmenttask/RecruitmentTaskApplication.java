package pl.ganda.recruitmenttask;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@Import(RecruitmentTaskConfiguration.class)
public class RecruitmentTaskApplication {

	public static void main(String[] args) {
		run(RecruitmentTaskApplication.class, args);
	}

}

package MARKETFUBY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //BaseTimeEntity적용 위함
public class MarketfubyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketfubyApplication.class, args);
	}

}

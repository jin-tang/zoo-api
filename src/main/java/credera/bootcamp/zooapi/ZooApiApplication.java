package credera.bootcamp.zooapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZooApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooApiApplication.class, args);
    }

}

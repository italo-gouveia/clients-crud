package br.com.snet.challenge;

import br.com.snet.challenge.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageConfig.class
})
public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }

}

package etf.unibl.org.ip.ipbackend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfiguration {
    @Bean
    public Executor executorService() {
        return Executors.newFixedThreadPool(5);
    }
}

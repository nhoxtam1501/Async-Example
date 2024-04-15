package com.ducku.asyncexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //The number of threads that the executor aims to maintain in the pool, even if they are idle.
        taskExecutor.setCorePoolSize(4);
        //This means up to 150 tasks can wait in the queue if all core threads are busy.
        taskExecutor.setQueueCapacity(150);
        /*If the queue reaches its full capacity and there are still more tasks being submitted,
         the thread pool will then create additional threads to handle the excess tasks,
         It's also important to note that threads above the core pool size are typically
         temporary and are meant to handle peak loads. -> can be terminated
         */
        taskExecutor.setMaxPoolSize(6);
        taskExecutor.setThreadNamePrefix("AsyncTaskThread-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}

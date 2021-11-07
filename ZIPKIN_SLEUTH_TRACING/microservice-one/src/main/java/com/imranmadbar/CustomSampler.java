package com.imranmadbar;


import brave.sampler.CountingSampler;
import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSampler {

    @Bean
    public static Sampler create() {
        return CountingSampler.create(0.5f);
    }


}

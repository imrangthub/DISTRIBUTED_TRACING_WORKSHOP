package com.imranmadbar;


import brave.sampler.CountingSampler;
import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSampler {

    @Value("${spring.zipkin.samplerProbRate:0.2}")
    private Float samplerProbRate;

    @Bean
    public Sampler create() {
        return CountingSampler.create(samplerProbRate);
    }


}

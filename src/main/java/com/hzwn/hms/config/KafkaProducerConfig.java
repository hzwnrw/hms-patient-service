package com.hzwn.hms.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class KafkaProducerConfig {
	
	@Autowired
    KafkaConfig kafkaConfig;

	@Bean
	public ProducerFactory<String, Object> producerFactory(KafkaProperties p) {
		Map<String, Object> producerProperties = kafkaConfig.producerProperties(p);
		return new DefaultKafkaProducerFactory<>(producerProperties);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate(KafkaProperties p) {
		return new KafkaTemplate<>(producerFactory(p));
	}
    
    
}

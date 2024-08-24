package com.hzwn.hms.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.hzwn.hms.patient.model.Patient;

@Component
public class KafkaProducerComponent {
	
	private static Logger log = LoggerFactory.getLogger(KafkaProducerComponent.class);
	
	@Autowired
	ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	public void publish(String topic, Patient request) {
		Message<Patient> message = MessageBuilder.withPayload(request).setHeader(KafkaHeaders.TOPIC, topic).build();
		log.debug(message.toString());
		applicationContext.getBean(KafkaTemplate.class).send(message);
	}
}

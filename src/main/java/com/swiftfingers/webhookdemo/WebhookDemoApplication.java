package com.swiftfingers.webhookdemo;

import com.swiftfingers.webhookdemo.model.Card;
import com.swiftfingers.webhookdemo.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class WebhookDemoApplication {
	@Autowired
	private EmailService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(WebhookDemoApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		emailSenderService.sendMail("jt.banego@gmail.com",
				"Payment Received.",
				"Your payment has been confirmed");

	}

}

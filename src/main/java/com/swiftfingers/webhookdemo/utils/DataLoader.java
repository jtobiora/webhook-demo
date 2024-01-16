package com.swiftfingers.webhookdemo.utils;

import com.swiftfingers.webhookdemo.model.Card;
import com.swiftfingers.webhookdemo.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* Loads or sets up mock card details for use in the database
* */
@Component
public class DataLoader implements CommandLineRunner {

    private final CardRepository cardRepository;

    public DataLoader(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Add a mock list of objects with attributes (id,name,card number, balance) to db
        List<Card> userList = List.of(
                new Card(1L, "John Maxwell", "1234-5678-9876-8947",1000.0),
                new Card(2L, "Angela James", "1234-6473-9897-2243",5000.0)
        );

        cardRepository.saveAll(userList);
    }
}

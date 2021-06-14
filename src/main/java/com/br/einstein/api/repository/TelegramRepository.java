package com.br.einstein.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.br.einstein.api.model.Telegram;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRepository extends MongoRepository<Telegram, Long> {


}
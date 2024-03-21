package com.picshop.catalog.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picshop.catalog.dto.KafkaOrderDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    //    @KafkaListener(topics = "pic-shop-order")
    @KafkaListener(topicPartitions =
        @TopicPartition(topic = "pic-shop-order", partitions = "0"),
            groupId = "order")
    /*@KafkaListener(topicPartitions =
            {
                    @TopicPartition(topic = "pic-shop-order", partitions = "0"),
                    @TopicPartition(topic = "pic-shop-order", partitions = "3")
            })*/
    public void readData(KafkaOrderDto kafkaOrderDto) {

    }


    // consumer01 ->  [partition-0] <- consumer02
    // group order:
    // consumer01
    // consumer02
}


/*
class P {
    @JsonProperty("aaaa")
    int aAaaA;
    String b;
    Order o;
    int version;
}

class C {
    @JsonProperty("aaaa")
    int a;
    String b;
}*/

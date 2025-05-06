package ru.hpclab.hl.module1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserKafkaMessage extends KafkaMessage {
    private User payload;

    public UserKafkaMessage() {
        this.entityType = EntityType.USER;
    }
}
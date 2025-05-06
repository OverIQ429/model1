package ru.hpclab.hl.module1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LikesKafkaMessage extends KafkaMessage {
    private Likes payload;

    public LikesKafkaMessage() {
        this.entityType = EntityType.LIKES;
    }
}
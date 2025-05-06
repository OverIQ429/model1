package ru.hpclab.hl.module1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostKafkaMessage extends KafkaMessage {
    private Post payload;

    public PostKafkaMessage() {
        this.entityType = EntityType.POST;
    }
}
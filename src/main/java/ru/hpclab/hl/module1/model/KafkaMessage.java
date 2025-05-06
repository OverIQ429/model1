package ru.hpclab.hl.module1.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "entityType",
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserKafkaMessage.class, name = "USER"),
        @JsonSubTypes.Type(value = PostKafkaMessage.class, name = "POST"),
        @JsonSubTypes.Type(value = LikesKafkaMessage.class, name = "LIKES")
})
public abstract class KafkaMessage {
    protected  EntityType entityType;
    private OperationType operation;
}
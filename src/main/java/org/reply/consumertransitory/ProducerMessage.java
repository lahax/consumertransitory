package org.reply.consumertransitory;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerMessage {
    String topic;
    @JsonProperty("value")
    String value;
}

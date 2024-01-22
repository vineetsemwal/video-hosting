package com.maveric.thinknxt.videohosting.serdes;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.maveric.thinknxt.videohosting.dto.SubscriberInfo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ListJsonDeserializer extends JsonDeserializer<Set<SubscriberInfo>> {

    @Override
    public Set<SubscriberInfo> deserialize(JsonParser jsonParser,
                                           DeserializationContext deserializationContext) throws IOException, JacksonException {
        Set<SubscriberInfo> subscriberInfos = new HashSet<>();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Long id = (Long) ((IntNode) node.get("id")).longValue();
        String firstName = node.get("firstName").asText();
        String lastName = node.get("lastName").asText();
        String email = node.get("email").asText();
        subscriberInfos.add(SubscriberInfo.builder()
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                .build());
        return subscriberInfos;
    }
}

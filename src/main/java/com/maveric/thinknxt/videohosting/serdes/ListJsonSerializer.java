package com.maveric.thinknxt.videohosting.serdes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.maveric.thinknxt.videohosting.dto.SubscriberInfo;

import java.io.IOException;
import java.util.Set;

public class ListJsonSerializer extends JsonSerializer<Set<SubscriberInfo>> {
    @Override
    public void serialize(Set<SubscriberInfo> subscriberInfos,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for(SubscriberInfo subscriberInfo: subscriberInfos) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("subscriberInfo", subscriberInfo);
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}

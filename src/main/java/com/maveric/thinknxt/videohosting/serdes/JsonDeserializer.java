package com.maveric.thinknxt.videohosting.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class JsonDeserializer<T> implements Deserializer<T> {

    private Class<T> destinationClass;

    public JsonDeserializer(Class<T> destinationClass) {
        this.destinationClass = destinationClass;
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (bytes == null)
            return null;

        try {
            return JsonMapper.readFromJson(new String(bytes, StandardCharsets.UTF_8), destinationClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.maveric.thinknxt.videohosting.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class JsonSerializer<T> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null)
            return null;

        try {
            return JsonMapper.writeToJson(data).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

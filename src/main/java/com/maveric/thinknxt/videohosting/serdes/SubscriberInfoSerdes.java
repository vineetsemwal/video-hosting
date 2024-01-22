package com.maveric.thinknxt.videohosting.serdes;

import com.maveric.thinknxt.videohosting.dto.SubscriberInfo;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class SubscriberInfoSerdes implements Serde<SubscriberInfo> {
    @Override
    public Serializer<SubscriberInfo> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<SubscriberInfo> deserializer() {
        return new JsonDeserializer<>(SubscriberInfo.class);
    }
}

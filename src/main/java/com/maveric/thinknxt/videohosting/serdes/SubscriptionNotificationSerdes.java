package com.maveric.thinknxt.videohosting.serdes;

import com.maveric.thinknxt.videohosting.dto.SubscriptionNotification;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class SubscriptionNotificationSerdes implements Serde<SubscriptionNotification> {
    @Override
    public Serializer<SubscriptionNotification> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<SubscriptionNotification> deserializer() {
        return new JsonDeserializer<>(SubscriptionNotification.class);
    }
}

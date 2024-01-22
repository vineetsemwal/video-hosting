package com.maveric.thinknxt.videohosting.processor;

import com.maveric.thinknxt.videohosting.dto.SubscriberInfo;
import com.maveric.thinknxt.videohosting.entity.Subscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriberProcessor {

    private static final String FUNCTION_DEFINITION = "subscription-out-0";
    private final StreamBridge streamBridge;

    public void subcribeChannel(Long channelId, Subscriber subscriber) {
        SubscriberInfo subscriberInfo = SubscriberInfo.builder()
                .id(subscriber.getId())
                .firstName(subscriber.getFirstName())
                .lastName(subscriber.getLastName())
                .email(subscriber.getEmail())
                .build();
        Message<SubscriberInfo> message = MessageBuilder.withPayload(subscriberInfo)
                .setHeader(KafkaHeaders.KEY, channelId)
                .build();
        streamBridge.send(FUNCTION_DEFINITION,message);
    }
}

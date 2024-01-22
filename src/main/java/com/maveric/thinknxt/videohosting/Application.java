package com.maveric.thinknxt.videohosting;

import com.maveric.thinknxt.videohosting.dto.SubscriberInfo;
import com.maveric.thinknxt.videohosting.dto.SubscriptionNotification;
import com.maveric.thinknxt.videohosting.serdes.ListJsonDeserializer;
import com.maveric.thinknxt.videohosting.serdes.SubscriberInfoSerdes;
import com.maveric.thinknxt.videohosting.serdes.SubscriptionNotificationSerdes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Video Hosting API", version = "2.3.0", description = "Documentation Customer API v1.0")
)
@Slf4j
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    @Bean
    public Serde<SubscriberInfo> subscriberInfoSerde() {
        return new SubscriberInfoSerdes();
    }

    @Bean
    public Serde<SubscriptionNotification> subscriptionNotificationSerde() {
        return new SubscriptionNotificationSerdes();
    }

    /*@Bean
    public Function<KStream<Long, SubscriberInfo>, KStream<Long, SubscriberInfo>> channel1Subscription() {

        Function<KStream<Long, SubscriberInfo>, KStream<Long, SubscriberInfo>> function = (inputStream) -> {
            KStream<Long, SubscriberInfo> filtered = inputStream
                    .filter((channelId, subscriberInfo) ->channelId.equals(1))
                    .peek((channelId, subscriberInfo) -> log.info("From subscription :: key : {} value: {}",channelId, subscriberInfo));
            return filtered.peek((channelId, subscriberInfo)-> log.info("key : {} value: {}",channelId, subscriberInfo));
        };
        return function;
    }*/

    @Bean
    public Consumer<KStream<Long, SubscriberInfo>> retrieve() {
        return (inputStream) -> inputStream.peek((key,value)->log.info("key: {} value: {}",key,value));
    }

    @Bean
    public Function<KStream<Long, SubscriberInfo>, KStream<Long, SubscriptionNotification>> subscribeNotification() {
        Function<KStream<Long, SubscriberInfo>, KStream<Long, SubscriptionNotification>> function = inputStream -> {
            KStream<Long, SubscriptionNotification> grouped = inputStream
                    .peek((key,value)->log.info("Before delivery:: key: {} value {}",key,value))
                    .groupByKey()
                    .aggregate(SubscriptionNotification::new,
                            (key,value,aggV)-> {
                                if(key != aggV.getChannelId()) {
                                    aggV.setChannelId(key);
                                }
                                aggV.getSubscriberInfoList().add(value);
                                return aggV;
                            },
                            Materialized.with(Serdes.Long(), subscriptionNotificationSerde()))
                    .toStream()
                    .peek((key,value)->log.info("After delivery:: key: {} value {}",key,value));
            return grouped;
        };
        return function;
    }

    /*@Bean
    public Serde<Windowed<String>>windowedStringSerde(){
        return WindowedSerdes.timeWindowedSerdeFrom(String.class,1);
    }

    @Bean
    public Function<KStream<Long, SubscriberInfo>, KStream<Windowed<String>, SubscriptionNotification>> wordsCountByKeyInWindow(){
        Function<KStream<Long, SubscriberInfo>, KStream<Windowed<String>, SubscriptionNotification>> function=inputStream->
                inputStream.groupByKey()

                        .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(1)))
                        .peek((windowKey,value)-> System.out.println("key="+windowKey.key()+" value="+value));
        return function;

    }*/
}

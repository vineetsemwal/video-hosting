package com.maveric.thinknxt.videohosting.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maveric.thinknxt.videohosting.serdes.ListJsonDeserializer;
import com.maveric.thinknxt.videohosting.serdes.ListJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionNotification {
    private Long channelId;
    private String channelOwnerEmail;
  //  @JsonIgnore
    private Set<SubscriberInfo> subscriberInfoList = new HashSet<>();
}

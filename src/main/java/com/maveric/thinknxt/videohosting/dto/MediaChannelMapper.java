package com.maveric.thinknxt.videohosting.dto;

import com.maveric.thinknxt.videohosting.entity.MediaChannel;

import java.time.LocalDateTime;

public class MediaChannelMapper {

    public static MediaChannel mapToMediaChannel(MediaChannelRequest mediaChannelRequest) {
        return MediaChannel.builder()
                .name(mediaChannelRequest.getName())
                .email(mediaChannelRequest.getEmail())
                .createdBy(mediaChannelRequest.getCreatedBy())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public static MediaChannelResponse mapToMediaChannelResponse(MediaChannel mediaChannel) {
        return MediaChannelResponse.builder()
                .id(mediaChannel.getId())
                .name(mediaChannel.getName())
                .email(mediaChannel.getEmail())
                .createdBy(mediaChannel.getCreatedBy())
                .createdDate(mediaChannel.getCreatedDate())
                .build();
    }
}

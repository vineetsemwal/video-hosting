package com.maveric.thinknxt.videohosting.service;

import com.maveric.thinknxt.videohosting.entity.MediaChannel;
import com.maveric.thinknxt.videohosting.exception.ResourceNotFoundException;
import com.maveric.thinknxt.videohosting.repository.MediaChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MediaChannelService {
    private final MediaChannelRepository mediaChannelRepository;

    public MediaChannel addMediaChannel(MediaChannel mediaChannel) {
        mediaChannel.setCreatedDate(LocalDateTime.now());
        return mediaChannelRepository.save(mediaChannel);
    }

    public MediaChannel getMediaChannel(Long channelId) {
        return mediaChannelRepository.findById(channelId).orElseThrow(()-> new ResourceNotFoundException("Channel not exists by given Id: "+channelId));
    }
}

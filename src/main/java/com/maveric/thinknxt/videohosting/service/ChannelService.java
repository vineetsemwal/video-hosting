package com.maveric.thinknxt.videohosting.service;

import com.maveric.thinknxt.videohosting.dto.MediaChannelMapper;
import com.maveric.thinknxt.videohosting.dto.MediaChannelRequest;
import com.maveric.thinknxt.videohosting.entity.MediaChannel;
import com.maveric.thinknxt.videohosting.exception.ResourceNotFoundException;
import com.maveric.thinknxt.videohosting.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;

    public MediaChannel addMediaChannel(MediaChannelRequest mediaChannelRequest) {
        MediaChannel mediaChannel = MediaChannelMapper.mapToMediaChannel(mediaChannelRequest);
        return channelRepository.save(mediaChannel);
    }

    public MediaChannel getMediaChannel(Long channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(()-> new ResourceNotFoundException("Channel not exists by given Id: "+channelId));
    }

}

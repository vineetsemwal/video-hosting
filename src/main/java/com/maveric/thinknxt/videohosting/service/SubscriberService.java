package com.maveric.thinknxt.videohosting.service;

import com.maveric.thinknxt.videohosting.dto.*;
import com.maveric.thinknxt.videohosting.entity.MediaChannel;
import com.maveric.thinknxt.videohosting.entity.Subscriber;
import com.maveric.thinknxt.videohosting.exception.ResourceNotFoundException;
import com.maveric.thinknxt.videohosting.processor.SubscriberProcessor;
import com.maveric.thinknxt.videohosting.repository.ChannelRepository;
import com.maveric.thinknxt.videohosting.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final ChannelRepository channelRepository;
    private final SubscriberRepository subscriberRepository;
    private final SubscriberProcessor subscriberProcessor;

    public SubscriberResponse registerSubscriber(SubscriberRequest subscriberRequest) {
        Subscriber subscriber = SubscriberMapper.mapToSubscriber(subscriberRequest);
        return SubscriberMapper.mapToSubscriberResponse(subscriberRepository.save(subscriber));
    }

    public void createSubscriber(SubscribeChannelRequest subscribeChannelRequest) {
        Subscriber subscriber = subscriberRepository.findByEmail(subscribeChannelRequest.getEmail());
        MediaChannel mediaChannel = channelRepository.findByName(subscribeChannelRequest.getSubscribeChannel())
                .orElseThrow(()-> new ResourceNotFoundException("Channel not exists with given name :"+subscribeChannelRequest.getSubscribeChannel()));
        if(Objects.nonNull(subscriber)) {
            subscriber.getMediaChannels().add(mediaChannel);
            System.out.println("****adding new media channel for subscription"+subscriber);
            subscriberRepository.save(subscriber);
            subscriberProcessor.subcribeChannel(mediaChannel.getId(), subscriber);
        }
    }


    /*public SubscriberResponse addSubscriber(SubscriberRequest subscriberRequest) {
        Subscriber subscriber = subscriberRepository.findByEmail(subscriberRequest.getEmail());
        MediaChannel mediaChannel = mediaChannelRepository.findByName(subscriberRequest.getSubscribedTo())
                .orElseThrow(()-> new ResourceNotFoundException("Channel not exists with given name :"+subscriberRequest.getSubscribedTo()));
        if(Objects.isNull(subscriber)) {
            Subscriber newSubscriber = Subscriber.builder()
                    .firstName(subscriberRequest.getFirstName())
                    .lastName(subscriberRequest.getLastName())
                    .email(subscriberRequest.getEmail())
                    .mediaChannelList(Arrays.asList(mediaChannel))
                    .build();
            return SubscriberMapper.mapToSubscriberResponse(subscriberRepository.save(newSubscriber));
        } else {
            subscriber.setFirstName(subscriberRequest.getFirstName());
            subscriber.setLastName(subscriberRequest.getLastName());
            subscriber.setEmail(subscriberRequest.getEmail());
            List<MediaChannel> channelList = subscriber.getMediaChannelList();
            if(channelList.isEmpty()) {
                subscriber.setMediaChannelList(Arrays.asList(mediaChannel));
            } else {
                channelList.add(mediaChannel);
                subscriber.setMediaChannelList(channelList);
            }
            return SubscriberMapper.mapToSubscriberResponse(subscriberRepository.save(subscriber));
        }
    }*/
}

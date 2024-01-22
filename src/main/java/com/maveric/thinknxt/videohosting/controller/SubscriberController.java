package com.maveric.thinknxt.videohosting.controller;

import com.maveric.thinknxt.videohosting.dto.SubscribeChannelRequest;
import com.maveric.thinknxt.videohosting.dto.SubscriberRequest;
import com.maveric.thinknxt.videohosting.dto.SubscriberResponse;
import com.maveric.thinknxt.videohosting.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriber")
public class SubscriberController {

    private final SubscriberService subscriberService;

    @PostMapping("/register")
    public ResponseEntity<SubscriberResponse> registerSubscriber(@RequestBody SubscriberRequest subscriberRequest) {
        return new ResponseEntity<>(subscriberService.registerSubscriber(subscriberRequest), HttpStatus.CREATED);
    }


    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeMediaChannel(@RequestBody SubscribeChannelRequest subscribeChannelRequest) {
        subscriberService.createSubscriber(subscribeChannelRequest);
        return ResponseEntity.ok("Message sent!!");
    }
}

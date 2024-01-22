package com.maveric.thinknxt.videohosting.controller;

import com.maveric.thinknxt.videohosting.dto.MediaChannelRequest;
import com.maveric.thinknxt.videohosting.entity.MediaChannel;
import com.maveric.thinknxt.videohosting.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/channel")
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<MediaChannel> addMediaChannel(@RequestBody MediaChannelRequest mediaChannelRequest) {
        return new ResponseEntity<>(channelService.addMediaChannel(mediaChannelRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaChannel> getMediaChannel(@PathVariable Long id) {
        return ResponseEntity.ok(channelService.getMediaChannel(id));
    }

}

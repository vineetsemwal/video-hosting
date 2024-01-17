package com.maveric.thinknxt.videohosting.controller;

import com.maveric.thinknxt.videohosting.entity.MediaChannel;
import com.maveric.thinknxt.videohosting.service.MediaChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/channel")
public class MediaChannelController {

    private final MediaChannelService mediaChannelService;

    @PostMapping("/create")
    public ResponseEntity<MediaChannel> addMediaChannel(@RequestBody MediaChannel mediaChannel) {
        return new ResponseEntity<>(mediaChannelService.addMediaChannel(mediaChannel), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaChannel> getMediaChannel(@PathVariable Long id) {
        return ResponseEntity.ok(mediaChannelService.getMediaChannel(id));
    }
}

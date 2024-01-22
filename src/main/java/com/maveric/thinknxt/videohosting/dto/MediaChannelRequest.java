package com.maveric.thinknxt.videohosting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaChannelRequest {
    private String name;
    private String email;
    private String createdBy;
}

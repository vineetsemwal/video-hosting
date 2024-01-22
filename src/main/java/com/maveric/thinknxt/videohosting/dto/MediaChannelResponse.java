package com.maveric.thinknxt.videohosting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaChannelResponse {
    private Long id;
    private String name;
    private String email;
    private String createdBy;
    private LocalDateTime createdDate;
}

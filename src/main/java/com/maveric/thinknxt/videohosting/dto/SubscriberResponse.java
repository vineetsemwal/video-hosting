package com.maveric.thinknxt.videohosting.dto;

import com.maveric.thinknxt.videohosting.entity.MediaChannel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

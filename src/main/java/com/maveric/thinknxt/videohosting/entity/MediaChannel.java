package com.maveric.thinknxt.videohosting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "channels")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;
    @Column(unique = true)
    private String name;
    private String email;
    private String createdBy;
    private LocalDateTime createdDate;
}

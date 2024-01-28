package com.maveric.thinknxt.videohosting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subscribers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriber_id")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

    /**
     * relationship should be many to many
     * one subscribe can subscribe multiple channels,
     * one channel can be subscribed by multiple subscribers
     */
    @JoinTable(
            name = "channel_subscribe",
            joinColumns = @JoinColumn(name = "subscriber_id", referencedColumnName = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
    )
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MediaChannel> mediaChannels = new HashSet<>();
}

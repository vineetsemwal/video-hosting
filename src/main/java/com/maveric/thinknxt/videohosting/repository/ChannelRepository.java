package com.maveric.thinknxt.videohosting.repository;

import com.maveric.thinknxt.videohosting.entity.MediaChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<MediaChannel, Long> {

    @Query("SELECT m FROM MediaChannel m WHERE m.name= :name")
    Optional<MediaChannel> findByName(String name);
}

package com.maveric.thinknxt.videohosting.repository;

import com.maveric.thinknxt.videohosting.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    @Query("SELECT s FROM Subscriber s WHERE s.email= :email")
    Subscriber findByEmail(String email);
}

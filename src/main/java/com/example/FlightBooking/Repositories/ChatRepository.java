package com.example.FlightBooking.Repositories;

import com.example.FlightBooking.Models.Chats;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface ChatRepository extends JpaRepository<Chats, Long> {
}

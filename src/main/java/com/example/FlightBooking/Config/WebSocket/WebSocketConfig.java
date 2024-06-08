package com.example.FlightBooking.Config.WebSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import java.net.http.WebSocket;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
            registry.addHandler(tutorialHandler(),"/ws").setAllowedOrigins("*");
    }
    @Bean
    WebSocketHandler tutorialHandler()
    {
        return new TutorialHandler();
    }
}
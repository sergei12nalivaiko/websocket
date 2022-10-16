package com.example.demo.controller;

import com.example.demo.entity.Greeting;
import com.example.demo.entity.HelloMessage;
import com.example.demo.entity.Message;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class MessageController {

    private UserService userService;
    private SimpMessagingTemplate simpleMessagingTemplate;

    @Autowired
    public MessageController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message){
        System.out.println("handling send message" + message + "to" + to);
        if(userService.isExist(to)){
            simpleMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }
}


package com.arxman.sandbox.kafkar.controller;

import com.arxman.sandbox.kafkar.representation.request.SendMessageRequestRepresentation;
import com.arxman.sandbox.kafkar.representation.request.SendObjectMessageARequestRepresentation;
import com.arxman.sandbox.kafkar.representation.request.SendObjectMessageBRequestRepresentation;
import com.arxman.sandbox.kafkar.service.StreamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private StreamService streamService;

    @PostMapping("/message/send")
    public void sendMessage(@RequestBody SendMessageRequestRepresentation requestBody) {
        streamService.sendMessage(requestBody.getMessage());
    }

    @PostMapping("/message/send-partition")
    public void sendMessageWithPartition(@RequestBody SendMessageRequestRepresentation requestBody) {
        streamService.sendMessage(requestBody.getPartition(), requestBody.getMessage());
    }

    @PostMapping("/message/object/message-a")
    public void sendObjectMessageA(@RequestBody SendObjectMessageARequestRepresentation requestBody) {
        streamService.sendMessageA(requestBody.getInfoA(), requestBody.getNumber());
    }

    @PostMapping("/message/object/message-b")
    public void sendObjectMessageB(@RequestBody SendObjectMessageBRequestRepresentation requestBody) {
        streamService.sendMessageB(requestBody.getInfoB(), requestBody.getNumber());
    }

}

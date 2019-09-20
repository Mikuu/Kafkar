package com.arxman.sandbox.kafkar.controller;

import com.arxman.sandbox.kafkar.service.StreamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ControlController {

    private final Logger logger = LoggerFactory.getLogger(ControlController.class);

    @Autowired
    private StreamService streamService;

    @PostMapping("/control/listener/pause")
    public void pauseListener() {
        streamService.pauseListener();
    }

    @PostMapping("/control/listener/resume")
    public void resumeListener() {
        streamService.resumeListener();
    }

    @PostMapping("/control/listener/start")
    public void startListener() {
        streamService.startListener();
    }

    @PostMapping("/control/listener/stop")
    public void stopListener() {
        streamService.stopListener();
    }

}

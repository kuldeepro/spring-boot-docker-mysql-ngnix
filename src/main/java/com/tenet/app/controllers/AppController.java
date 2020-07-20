package com.tenet.app.controllers;

import com.tenet.app.models.responses.ConnectionDetailsResponse;
import com.tenet.app.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @Autowired
    DBService dbService;

    @GetMapping("/get-db-details")
    public ConnectionDetailsResponse getDBDetails() {

        return dbService.getConnectionDetails();
    }

    @GetMapping("/ping")
    public String ping() {

        return "Hey, I am alive";
    }
}

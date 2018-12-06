package com.sbawa.client.api.elastic.controller;

import com.sbawa.client.api.elastic.client.GraphQLClient;
import com.sbawa.client.api.elastic.model.QueryPersonById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Just a controller to test end to end integration with GraphQl client-server applications.
 *
 */
@RestController
public class ClientController {

    @Autowired
    private GraphQLClient client;

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity getData() {
        QueryPersonById person = client.queryPersonById();
        return ResponseEntity.ok(person);
    }



    @RequestMapping(value = "/mutate", method = RequestMethod.GET)
    public ResponseEntity createData() {
        QueryPersonById person = client.mutatePersonNameAge();
        return ResponseEntity.ok(person);
    }


}

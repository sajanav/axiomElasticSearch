package com.search_service_es.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search_service_es.dto.SearchDevice;
import com.search_service_es.intf.service_intf;

@RestController
public class Controller {

    @Autowired
    public service_intf serv;

    @PostMapping("/ingest")
    public HttpStatus ingest_data() throws IOException, InterruptedException {
        return serv.ingest_data();
    }

    @GetMapping("/example")
    public List<SearchDevice> get_all_data() throws IOException {
        return serv.get_all_data();
    }

    @GetMapping("/search/{searchString}")
    public List<SearchDevice> get_specific_docs(@PathVariable String searchString) throws IOException {
        return serv.get_specific_docs(searchString);
    }

}

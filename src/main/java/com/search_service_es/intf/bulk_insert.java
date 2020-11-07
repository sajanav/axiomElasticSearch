package com.search_service_es.intf;

import com.search_service_es.dto.SearchDevice;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

public interface bulk_insert {
    HttpStatus ingestdata(List<SearchDevice> employee) throws InterruptedException, IOException;
}

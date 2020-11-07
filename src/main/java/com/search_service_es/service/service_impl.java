package com.search_service_es.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.search_service_es.dto.SearchDevice;
import com.search_service_es.intf.service_intf;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class service_impl implements service_intf {

    @Autowired
    BulkInsert blk;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public HttpStatus ingest_data() throws IOException, InterruptedException {
    	 //Here Enter path to your Json File.
        File jsonFile = new File("Mobiledatabase.json");
        FileReader fr = new FileReader(jsonFile);   //reads the file
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<SearchDevice> deviceList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Integer count = 1;
        while ((line = br.readLine()) != null) {
        	SearchDevice device = mapper.readValue(line, SearchDevice.class);
        	device.setId(count);
        	deviceList.add(device);
            count++;
        }
        blk.ingestdata(deviceList);
        return HttpStatus.OK;
    }

    @Override
    public List<SearchDevice> get_all_data() throws IOException {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("device_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(25);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHit = searchResponse.getHits().getHits();
        List<SearchDevice> deviceList = new ArrayList<>();
        for (SearchHit hit : searchHit) {
        	deviceList.add(objectMapper.convertValue(hit.getSourceAsMap(), SearchDevice.class));
        }
        return deviceList;
    }

    @Override
    public List<SearchDevice> get_specific_docs(String search_string) throws IOException {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.termQuery("brand", search_string));
        boolQueryBuilder.should(QueryBuilders.termQuery("phone", search_string));
        boolQueryBuilder.should(QueryBuilders.matchQuery("picture", search_string).boost(0.4f)
                .fuzziness(Fuzziness.AUTO));
        boolQueryBuilder.should(QueryBuilders.matchQuery("announceDate", search_string).boost(0.3f)
                .fuzziness(Fuzziness.AUTO));
        boolQueryBuilder.should(QueryBuilders.matchQuery("priceEur", search_string).boost(0.4f)
                .fuzziness(Fuzziness.AUTO));
        boolQueryBuilder.should(QueryBuilders.matchQuery("sim", search_string).boost(0.3f)
                .fuzziness(Fuzziness.AUTO));
        boolQueryBuilder.should(QueryBuilders.matchQuery("resolution", search_string).boost(0.4f)
                .fuzziness(Fuzziness.AUTO));
        boolQueryBuilder.should(QueryBuilders.matchQuery("audioJack", search_string).boost(0.3f)
                .fuzziness(Fuzziness.AUTO));
        boolQueryBuilder.should(QueryBuilders.matchQuery("gps", search_string).boost(0.4f)
                .fuzziness(Fuzziness.AUTO));
        boolQueryBuilder.should(QueryBuilders.matchQuery("battery", search_string).boost(0.4f)
                .fuzziness(Fuzziness.AUTO));	
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("device_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHit = searchResponse.getHits().getHits();
        List<SearchDevice> employeeList = new ArrayList<>();
        for (SearchHit hit : searchHit) {
            employeeList.add(objectMapper.convertValue(hit.getSourceAsMap(), SearchDevice.class));
        }
        return employeeList;
    }
}

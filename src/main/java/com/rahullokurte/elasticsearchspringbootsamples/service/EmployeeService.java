package com.rahullokurte.elasticsearchspringbootsamples.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahullokurte.elasticsearchspringbootsamples.model.EmployeeDocument;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EmployeeService {

  RestHighLevelClient restHighLevelClient;
  private ObjectMapper objectMapper;

  public EmployeeService(
      @Qualifier("elasticsearchClient") RestHighLevelClient restHighLevelClient,
      ObjectMapper objectMapper) {
    this.restHighLevelClient = restHighLevelClient;
    this.objectMapper = objectMapper;
  }

  public String createEmployeeDocument(EmployeeDocument document) throws IOException {
    UUID uuid = UUID.randomUUID();
    document.setId(uuid.toString());
    Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);
    IndexRequest request = new IndexRequest("employee").id(document.getId()).source(documentMapper);

    IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);

    return response.getResult().name();
  }

  public List<EmployeeDocument> findAll() throws Exception {
    SearchRequest searchRequest = new SearchRequest();
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    searchRequest.source(searchSourceBuilder);
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    return getSearchResult(searchResponse);
  }

  private List<EmployeeDocument> getSearchResult(SearchResponse response) {
    SearchHit[] searchHit = response.getHits().getHits();
    List<EmployeeDocument> employeeDocuments = new ArrayList<>();
    if (searchHit.length > 0) {
      Arrays.stream(searchHit)
          .forEach(
              hit ->
                  employeeDocuments.add(
                      objectMapper.convertValue(hit.getSourceAsMap(), EmployeeDocument.class)));
    }
    return employeeDocuments;
  }
}

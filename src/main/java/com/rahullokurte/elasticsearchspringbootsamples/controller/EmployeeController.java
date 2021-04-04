package com.rahullokurte.elasticsearchspringbootsamples.controller;

import com.rahullokurte.elasticsearchspringbootsamples.model.EmployeeDocument;
import com.rahullokurte.elasticsearchspringbootsamples.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  private EmployeeService service;

  @Autowired
  public EmployeeController(EmployeeService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity createEmployee(@RequestBody EmployeeDocument document) throws IOException {
      return new ResponseEntity(service.createEmployeeDocument(document), HttpStatus.CREATED);
  }

  @GetMapping
  public List<EmployeeDocument> findAll() throws Exception {
    return service.findAll();
  }
}

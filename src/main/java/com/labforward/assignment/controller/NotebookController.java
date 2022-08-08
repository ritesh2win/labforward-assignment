package com.labforward.assignment.controller;

import com.labforward.assignment.model.NotebookRequest;
import com.labforward.assignment.model.NotebookResponse;
import com.labforward.assignment.service.NotebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotebookController implements NotebookApi {

  private final Logger logger = LoggerFactory.getLogger(NotebookController.class);

  private NotebookService notebookService;

  @Autowired
  public NotebookController(NotebookService notebookService) {
    this.notebookService = notebookService;
  }

  @Override
  public ResponseEntity<NotebookResponse> getWordCountWithSuggestion(
      NotebookRequest notebookRequest) {
    ResponseEntity<NotebookResponse> notebookResponseEntity =
        new ResponseEntity<>(
            notebookService.getWordCountWithSuggestion(notebookRequest), HttpStatus.OK);
    return notebookResponseEntity;
  }
}

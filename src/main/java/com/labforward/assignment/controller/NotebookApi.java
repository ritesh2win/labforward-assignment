package com.labforward.assignment.controller;

import com.labforward.assignment.model.NotebookRequest;
import com.labforward.assignment.model.NotebookResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface NotebookApi {

  @PostMapping(value = "/labforward/getWordCountWithSuggestion/")
  default ResponseEntity<NotebookResponse> getWordCountWithSuggestion(
      @RequestBody @Valid NotebookRequest notebookRequest) {
    return new ResponseEntity<>(new NotebookResponse(), HttpStatus.NOT_IMPLEMENTED);
  }
}

package com.labforward.assignment.model;

import java.util.List;

public class NotebookResponse {

  private long frequency;
  private List<String> similarWords;

  public long getFrequency() {
    return frequency;
  }

  public void setFrequency(long frequency) {
    this.frequency = frequency;
  }

  public List<String> getSimilarWords() {
    return similarWords;
  }

  public void setSimilarWords(List<String> similarWords) {
    this.similarWords = similarWords;
  }

  public NotebookResponse frequency(long frequency) {
    this.frequency = frequency;
    return this;
  }

  public NotebookResponse similarWords(List<String> similarWords) {
    this.similarWords = similarWords;
    return this;
  }
}

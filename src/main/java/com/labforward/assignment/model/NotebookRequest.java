package com.labforward.assignment.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NotebookRequest {

  private String word;
  private List<String> entryWords;

  @NotNull
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Word should consists alphabets only")
  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public List<String> getEntryWords() {
    return entryWords;
  }

  public void setEntryWords(List<String> entryWords) {
    this.entryWords = entryWords;
  }

  public NotebookRequest word(String word) {
    this.word = word;
    return this;
  }

  public NotebookRequest entryWords(List<String> entryWords) {
    this.entryWords = entryWords;
    return this;
  }
}

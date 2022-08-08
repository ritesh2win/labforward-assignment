package com.labforward.assignment.service;

import static com.labforward.assignment.constant.GenericConstants.*;

import com.labforward.assignment.logging.LogBuilder;
import com.labforward.assignment.model.NotebookRequest;
import com.labforward.assignment.model.NotebookResponse;
import com.labforward.assignment.util.NotebookUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotebookService {

  private NotebookUtil notebookUtil;

  private final Logger LOGGER = LoggerFactory.getLogger(NotebookService.class);

  @Autowired
  public NotebookService(NotebookUtil notebookUtil) {
    this.notebookUtil = notebookUtil;
  }

  public NotebookResponse getWordCountWithSuggestion(NotebookRequest notebookRequest) {
    LOGGER.info(
        new LogBuilder(NOTEBOOK_EVENT, GET_FREQUENCY_STEP)
            .detailMessage("started fetching word list")
            .toString());
    List<String> givenWords = notebookRequest.getEntryWords();
    LOGGER.info(
        new LogBuilder(NOTEBOOK_EVENT, GET_FREQUENCY_STEP)
            .detailMessage("finished fetching word list")
            .toString());
    Map<String, Long> wordFrequencyMap =
        givenWords.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    LOGGER.info(
        new LogBuilder(NOTEBOOK_EVENT, GET_FREQUENCY_STEP)
            .detailMessage("finished fetching word list")
            .toString());
    return new NotebookResponse()
        .frequency(
            wordFrequencyMap.get(notebookRequest.getWord()) == null
                ? 0L
                : wordFrequencyMap.get(notebookRequest.getWord()))
        .similarWords(getSimilarWords(notebookRequest.getWord(), givenWords));
  }

  private List<String> getSimilarWords(String word, List<String> givenWords) {
    LOGGER.info(
        new LogBuilder(NOTEBOOK_EVENT, GET_SIMILAR_WORDS_STEP)
            .detailMessage("started fetching similar word list")
            .toString());
    List<String> similarString = new ArrayList<>();
    for (String givenWord : givenWords) {
      if (notebookUtil.calculateLevenshteinDistance(word, givenWord) <= 1)
        similarString.add(givenWord);
    }
    LOGGER.info(
        new LogBuilder(NOTEBOOK_EVENT, GET_SIMILAR_WORDS_STEP)
            .detailMessage("finished fetching similar word list")
            .toString());
    return similarString.stream().filter(w -> !word.equals(w)).collect(Collectors.toList());
  }
}

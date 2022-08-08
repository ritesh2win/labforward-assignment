package com.labforward.assignment.util;

import javax.validation.ValidationException;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Component;

@Component
public class NotebookUtil {

  public int calculateLevenshteinDistance(String text1, String text2) throws ValidationException {
    if (text1 == null || text2 == null) throw new ValidationException("Input words can't be null");
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return levenshteinDistance.apply(text1, text2);
  }
}

package com.labforward.assignment.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
@SpringBootTest
public class NotebookUtilTest {

    @InjectMocks private NotebookUtil notebookUtil;

    @Test
    void testCalculateLevenshteinDistance_withSimilarWords_shouldReturnZero()
    {
        assertThat(notebookUtil.calculateLevenshteinDistance("tiger","tiger")).isEqualTo(0);
    }

    @Test
    void testCalculateLevenshteinDistance_withDifferentWords_shouldReturnGreaterThanZero()
    {
        assertThat(notebookUtil.calculateLevenshteinDistance("rose","horse")).isGreaterThan(0);
    }

}

package com.labforward.assignment.service;

import com.labforward.assignment.util.NotebookUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ValidationException;

import static com.labforward.assignment.common.TestCommon.getInvalidNotebookRequest;
import static com.labforward.assignment.common.TestCommon.getValidNotebookRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@SpringBootTest
public class NotebookServiceTest {

    @Mock
    NotebookUtil notebookUtil;

    @InjectMocks
    NotebookService notebookService;

    @Test
    void testGetWordCountWithSuggestion_withValidNotebookRequest_shouldBeSuccess()
    {
        when(notebookUtil.calculateLevenshteinDistance(anyString(),anyString())).thenReturn(1);
        assertThat(notebookService.getWordCountWithSuggestion(getValidNotebookRequest())).isNotNull();
    }

    @Test
    void testGetWordCountWithSuggestion_withInvalidNotebookRequest_shouldThrowException()
    {
        when(notebookUtil.calculateLevenshteinDistance(anyString(),anyString())).thenThrow(new ValidationException());
        assertThrows(ValidationException.class, () -> notebookService.getWordCountWithSuggestion(getInvalidNotebookRequest()));
    }



}

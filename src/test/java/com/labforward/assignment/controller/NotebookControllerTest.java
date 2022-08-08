package com.labforward.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labforward.assignment.model.NotebookRequest;
import com.labforward.assignment.model.NotebookResponse;
import com.labforward.assignment.service.NotebookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

import static com.labforward.assignment.common.TestCommon.getInvalidNotebookRequest;
import static com.labforward.assignment.common.TestCommon.getValidNotebookRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class NotebookControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean private NotebookService notebookService;

    @BeforeEach
    void setup()
    {

    }

    @DisplayName("Notebook Controller Test Success")
    @Test
    void testGetWordCountWithSuggestion_withValidInput_shouldBeSuccess() throws Exception {
        when(notebookService.getWordCountWithSuggestion(any())).thenReturn(new NotebookResponse());
        mockMvc.perform(post(UriComponentsBuilder.fromPath("/labforward/getWordCountWithSuggestion/").toUriString())
                        .content(objectMapper.writeValueAsString(getValidNotebookRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(notebookService,times(1)).getWordCountWithSuggestion(any());
    }

    @DisplayName("Notebook Controller Test Failed")
    @Test
    void testGetWordCountWithSuggestion_withInvalidInput_shouldBeFailed() throws Exception {
        when(notebookService.getWordCountWithSuggestion(any())).thenReturn(new NotebookResponse());
        mockMvc.perform(post(UriComponentsBuilder.fromPath("/labforward/getWordCountWithSuggestion/").toUriString())
                        .content(objectMapper.writeValueAsString(getInvalidNotebookRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        verify(notebookService,times(0)).getWordCountWithSuggestion(any());
    }



}

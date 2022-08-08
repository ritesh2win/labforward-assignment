package com.labforward.assignment.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labforward.assignment.model.NotebookRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"integration"})
@AutoConfigureMockMvc
public class NotebookIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @DisplayName("Notebook Integration PositveTests")
    @ParameterizedTest(name = "{0}")
    @MethodSource("getValidNotebookInput")
    void  testGetWordCountWithSuggestion_Success(String name, NotebookRequest notebookRequest, int frequecy, String... suggestedWords) throws Exception {
        mockMvc.perform(post(UriComponentsBuilder.fromPath("/labforward/getWordCountWithSuggestion/").toUriString())
                .content(objectMapper.writeValueAsString(notebookRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("frequency",notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("frequency",is(frequecy)))
                .andExpect(MockMvcResultMatchers.jsonPath("similarWords",notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("similarWords",containsInAnyOrder(suggestedWords)));
    }

    private static Stream<Arguments> getValidNotebookInput() {
        return Stream.of(
                Arguments.of(
                        "Valid Notebook Request 1",
                                new NotebookRequest().word("Word").entryWords(Arrays.asList("Word","Word","Word","word")),
                                    3,
                                    new String[]{"word"}
                ),
                Arguments.of(
                        "Valid Notebook Request 2",
                        new NotebookRequest().word("Word").entryWords(Arrays.asList("Word","Words","Wor","word")),
                        1,
                        new String[]{"Words", "Wor", "word"}
                ),
                Arguments.of(
                        "Valid Notebook Request 3",
                        new NotebookRequest().word("rose").entryWords(Arrays.asList("horse","rose","rise","nose")),
                        1,
                        new String[]{"rise","nose"}
                )
        );

    }

    @DisplayName("Notebook Integration Negative Tests")
    @ParameterizedTest(name = "{0}")
    @MethodSource("getInvalidNotebookInput")
    void  testGetWordCountWithSuggestion_Failed(String name, NotebookRequest notebookRequest, String code, String status, String errorMessage) throws Exception {
        mockMvc.perform(post(UriComponentsBuilder.fromPath("/labforward/getWordCountWithSuggestion/").toUriString())
                        .content(objectMapper.writeValueAsString(notebookRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code",is(code)))
                .andExpect(MockMvcResultMatchers.jsonPath("status",is(status)))
                .andExpect(MockMvcResultMatchers.jsonPath("message",is(errorMessage)));
    }

    private static Stream<Arguments> getInvalidNotebookInput() {
        return Stream.of(
                Arguments.of(
                        "Invalid Notebook Request containing special character",
                        new NotebookRequest().word("Word@").entryWords(Arrays.asList("Word","Word","Word","word")),
                        "400",
                        "error",
                        "Validation Failed"
                )
        );

    }



}

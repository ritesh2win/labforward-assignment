package com.labforward.assignment.common;

import com.labforward.assignment.model.NotebookRequest;

import java.util.Arrays;

public class TestCommon {
    public static NotebookRequest getValidNotebookRequest() {
        return new NotebookRequest().word("Word").entryWords(Arrays.asList("Word","Word","Word","word"));
    }
    public static NotebookRequest getInvalidNotebookRequest() {
        return new NotebookRequest().word("Wor@d").entryWords(Arrays.asList("Word","Word","Word","word"));
    }

}

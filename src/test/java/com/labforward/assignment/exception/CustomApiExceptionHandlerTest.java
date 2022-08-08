package com.labforward.assignment.exception;

import com.labforward.assignment.controller.NotebookController;
import com.labforward.assignment.model.NotebookRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomApiExceptionHandlerTest {
    @InjectMocks private CustomApiExceptionHandler customApiExceptionHandler;

    @Test
    void testHandleArgumentNotValidException_withMethodArgumentNotValidException_shouldBeHandled() throws NoSuchMethodException {
        BindingResult bindingResult= new BeanPropertyBindingResult(new NotebookRequest(), "notebookRequest");
        bindingResult.addError(new FieldError("field","word","test"));
        Method method = NotebookController.class.getMethod("getWordCountWithSuggestion",NotebookRequest.class);
        MethodParameter methodParameter = new MethodParameter(method,0);
        MethodArgumentNotValidException exception= new MethodArgumentNotValidException(methodParameter,bindingResult);
        ResponseEntity<Fault> faultResponseEntity= customApiExceptionHandler.handleArgumentNotValidException(exception);
        assertThat(faultResponseEntity.getStatusCode().is4xxClientError()).isTrue();
        assertThat(faultResponseEntity.getBody().getCode()).isEqualTo("400");
        assertThat(faultResponseEntity.getBody().getMessage()).isEqualTo("Validation Failed");
        assertThat(faultResponseEntity.getBody().getStatus()).isEqualTo("error");
    }
}

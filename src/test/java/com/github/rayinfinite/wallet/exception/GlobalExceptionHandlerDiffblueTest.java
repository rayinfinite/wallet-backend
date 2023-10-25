package com.github.rayinfinite.wallet.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.rayinfinite.wallet.model.BaseResponse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

@ContextConfiguration(classes = {GlobalExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class GlobalExceptionHandlerDiffblueTest {
    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    /**
     * Method under test: {@link GlobalExceptionHandler#handleBindException(BindException)}
     */
    @Test
    void testHandleBindException() {
        ArrayList<ObjectError> objectErrorList = new ArrayList<>();
        objectErrorList.add(new ObjectError("Object Name", "Default Message"));
        BindException e = mock(BindException.class);
        when(e.getAllErrors()).thenReturn(objectErrorList);
        BaseResponse<String> actualHandleBindExceptionResult = globalExceptionHandler.handleBindException(e);
        assertEquals(40000, actualHandleBindExceptionResult.getCode());
        assertEquals("Default Message", actualHandleBindExceptionResult.getMessage());
        assertNull(actualHandleBindExceptionResult.getData());
        verify(e).getAllErrors();
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleDefaultException(DefaultException)}
     */
    @Test
    void testHandleDefaultException() {
        BaseResponse<String> actualHandleDefaultExceptionResult = globalExceptionHandler
                .handleDefaultException(new DefaultException("An error occurred"));
        assertEquals(40000, actualHandleDefaultExceptionResult.getCode());
        assertEquals("An error occurred", actualHandleDefaultExceptionResult.getMessage());
        assertNull(actualHandleDefaultExceptionResult.getData());
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleDefaultException(DefaultException)}
     */
    @Test
    void testHandleDefaultException2() {
        DefaultException e = mock(DefaultException.class);
        when(e.getMessage()).thenReturn("Not all who wander are lost");
        BaseResponse<String> actualHandleDefaultExceptionResult = globalExceptionHandler.handleDefaultException(e);
        assertEquals(40000, actualHandleDefaultExceptionResult.getCode());
        assertEquals("Not all who wander are lost", actualHandleDefaultExceptionResult.getMessage());
        assertNull(actualHandleDefaultExceptionResult.getData());
        verify(e).getMessage();
    }
}


package com.github.rayinfinite.wallet.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DefaultExceptionDiffblueTest {
    /**
     * Method under test: {@link DefaultException#DefaultException(String)}
     */
    @Test
    void testConstructor() {
        DefaultException actualDefaultException = new DefaultException("An error occurred");
        assertNull(actualDefaultException.getCause());
        assertEquals(0, actualDefaultException.getSuppressed().length);
        assertEquals("An error occurred", actualDefaultException.getMessage());
        assertEquals("An error occurred", actualDefaultException.getLocalizedMessage());
    }
}


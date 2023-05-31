package com.global.automotivebackend.controller.secured;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CookieToJwtConverterTest extends CookieToJwtConverter {



    @Test
    public void testGetTokenFromCookie_ReturnsToken_WhenCookieExists() {
        // Arrange
        String expectedToken = "exampleToken";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cookie[] cookies = new Cookie[] { new Cookie("MyJWT", expectedToken) };
        when(request.getCookies()).thenReturn(cookies);

        // Act
        String actualToken = getTokenFromCookie(request);

        // Assert
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testGetTokenFromCookie_ReturnsNull_WhenCookieNotExists() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getCookies()).thenReturn(null);

        // Act
        String actualToken = getTokenFromCookie(request);

        // Assert
        assertNull(actualToken);
    }

    @Test
    public void testGetTokenFromCookie_ReturnsNull_WhenCookieNameDoesNotMatch() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cookie[] cookies = new Cookie[] { new Cookie("OtherCookie", "exampleToken") };
        when(request.getCookies()).thenReturn(cookies);

        // Act
        String actualToken = getTokenFromCookie(request);

        // Assert
        assertNull(actualToken);
    }

}

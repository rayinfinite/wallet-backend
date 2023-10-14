package com.github.rayinfinite.wallet.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtConfig jwtConfig;
    private final CurrentSession currentSession;
    private final UserService userService;


    /**
     * 如果请求不含有login，从请求头中提取token，验证token，将token中的用户信息存入session
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("RequestURI " + request.getMethod() + " " + request.getRequestURI());
        List<String> excludedURIs = Arrays.asList("login", "swagger-ui", "v3/api-docs");
        boolean isExcluded = excludedURIs.stream().anyMatch(uri -> request.getRequestURI().contains(uri));
        if (!isExcluded) {
            try {
                String token = extractToken(request);
                if (!StringUtils.hasText(token)) {
                    return;
                }
                if (!token.equals(currentSession.getToken()) || currentSession.getUser() == null || currentSession.getBook() == null) {
                    currentSession.setToken(token);
                    userService.get(jwtConfig.validateToken(token));
                }
            } catch (JWTVerificationException | IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(jwtConfig.getTokenPrefix())) {
            return header.replaceFirst(Pattern.quote(jwtConfig.getTokenPrefix()), "");
        }
        return null;
    }
}

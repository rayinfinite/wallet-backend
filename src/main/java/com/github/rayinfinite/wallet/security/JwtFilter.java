package com.github.rayinfinite.wallet.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtConfig jwtConfig;
    private final CurrentSession currentSession;
    private final UserRepository userRepository;

    /**
     * 如果请求不含有login，从请求头中提取token，验证token，将token中的用户信息存入session
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().contains("login")) {
            try {
                String token = extractToken(request);
                if (StringUtils.hasText(token) && !token.equals(currentSession.getToken())) {
                    currentSession.setToken(token);
                    User user = userRepository.findById(jwtConfig.validateToken(token))
                            .orElseThrow(IllegalArgumentException::new);
                    currentSession.setUser(user);
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

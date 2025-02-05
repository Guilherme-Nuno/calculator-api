package com.guilherme.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(MDCFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UUID id = UUID.randomUUID();

        MDC.put("X-Request-ID", id.toString());
        logger.info("MDC Request ID added");
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("X-request-ID");
        }
    }
}

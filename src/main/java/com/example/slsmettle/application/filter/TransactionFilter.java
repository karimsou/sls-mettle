package com.example.slsmettle.application.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
public class TransactionFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String ipAddress = req.getRemoteAddr();
        LOG.info("IP Address is :{}", ipAddress);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    // other methods
}


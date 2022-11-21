package vn.codingt.clean.presenter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.codingt.clean.core.util.constant.ApiPath;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j
public class LogTimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest rep = (HttpServletRequest) request;
        String path = rep.getServletPath();
        if (path.contains(ApiPath.API_URL_ROOT)) {
            Instant start = Instant.now();
            chain.doFilter(request, response);
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            log.info("Request Method: [{}] Request URL: [{}] took [{}] ms", rep.getMethod(), rep.getRequestURI(),
                    timeElapsed);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

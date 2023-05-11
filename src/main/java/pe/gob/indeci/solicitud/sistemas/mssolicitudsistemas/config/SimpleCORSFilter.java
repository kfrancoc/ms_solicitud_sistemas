package pe.gob.indeci.solicitud.sistemas.mssolicitudsistemas.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("*"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST,    PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Cache-Control, Authorization, X-Authorization, Content-Type, Accept, X-Requested-With, remember-me");

        if (notPreflight(request)) {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean notPreflight(HttpServletRequest request) {
        return !"OPTIONS".equals(request.getMethod());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}

package com.lab409.socket.demoServer.web.fiter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "myFilter",urlPatterns = "/*",initParams = {
        @WebInitParam(name = "server",value="www.baidu.com"),
        @WebInitParam(name="port",value="443")
})
public class MyFilter implements Filter {
    @Override
    public void destroy() {
        //System.out.println("myfilter destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //System.out.println("this is MyFilter, url :");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        //System.out.println("myfilter init");
    }
}

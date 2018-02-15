
package com.hit.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(servletNames = {"ToDoController"})
public class MyFilter implements Filter {

    /**
     * This function called just once and it's when the servlet destroyed
     */
    public void destroy() {

    }

    /**
     * doFilter function called in every action between jsp and servlet
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        chain.doFilter(request, response);
        String url = null;
        String queryString = null;
        if (request instanceof HttpServletRequest) {
            url = ((HttpServletRequest) request).getRequestURL().toString();
            queryString = ((HttpServletRequest) request).getQueryString();
        }
        long endTime = System.currentTimeMillis();
        long replyTime = endTime - startTime;
        if (queryString == null)
            System.out.println("servlet reply time: " + replyTime + "MS to loading " + url);
        else
            System.out.println("servlet reply time: " + replyTime + "MS to loading " + url + "?" + queryString);

    }

    /**
     * This function called just once and it's when the servlet created
     */
    public void init(FilterConfig config) throws ServletException {

    }

}


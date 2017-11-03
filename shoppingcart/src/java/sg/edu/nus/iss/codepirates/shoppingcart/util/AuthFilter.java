/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.codepirates.shoppingcart.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Prasanna
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/faces/*"} )
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("Filter Init()......!!!!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
         HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String reqURI = req.getRequestURI();
        if ((reqURI.indexOf("/faces/welcome.xhtml") >= 0 || reqURI.indexOf("/faces/login.xhtml") >= 0 || reqURI.indexOf("/faces/register.xhtml") >= 0)  || (session != null && session.getAttribute("username") != null)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
        }
       
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy()....!!!!");
    }
    
}

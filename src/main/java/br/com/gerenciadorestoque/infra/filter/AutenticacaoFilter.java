package br.com.gerenciadorestoque.infra.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class AutenticacaoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        String loginURI = httpRequest.getContextPath() + "/login.jsp";
        String loginServletURI = httpRequest.getContextPath() + "/loginServlet";

        boolean loggedIn = (session != null && session.getAttribute("usuario") != null);
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI) || httpRequest.getRequestURI().equals(loginServletURI);
        boolean isStaticResource = httpRequest.getRequestURI().matches(".*(css|jpg|png|gif|js)$");

        if (loggedIn || loginRequest || isStaticResource) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}

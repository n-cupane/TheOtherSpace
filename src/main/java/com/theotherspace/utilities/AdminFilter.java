package com.theotherspace.utilities;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

import com.theotherspace.model.User;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(servletNames = {"UserControlPanelAddServlet", "UserControlPanelEditServlet", "UserControlPanelServlet",
		"ScreeningControlPanelAddServlet", "ScreeningControlPanelEditServlet", "AddMovieServlet",
		"ScreeningControlPanelServlet", "SearchMovieServlet", "TheaterControlPanelAddServlet",
		"TheaterControlPanelEditServlet", "TheaterControlPanelServlet", "MovieControlPanelServlet"})
public class AdminFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdminFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		User activeUser = (User) request.getSession().getAttribute("activeUser");
		if (activeUser.getId() != 1) {
			response.sendRedirect("HomePageServlet");
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

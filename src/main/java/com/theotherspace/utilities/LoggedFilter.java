package com.theotherspace.utilities;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.theotherspace.model.User;


/**
 * Servlet Filter implementation class LoggedFilter
 */
@WebFilter(servletNames = {"AcceptCardServlet", "AccountDeletionServlet", "AccountInfoServlet",
		"AddFavoriteServlet", "AddMovieServlet", "BookingServlet", "CheckOutServlet", "ConfirmationServlet",
		"ConfirmReviewServlet", "LogoutServlet", "MovieControlPanelServlet", "MyAccountServlet",
		"MyNextTicketServlet", "MyTicketServlet", "PasswordForgottenServlet", "RemoveReviewServlet",
		"ReviewServlet", "ScreeningControlPanelAddServlet", "ScreeningControlPanelEditServlet",
		"ScreeningControlPanelServlet", "SearchMovieServlet", "TheaterControlPanelAddServlet",
		"TheaterControlPanelEditServlet", "TheaterControlPanelServlet", "TicketDownloadServlet",
		"UserControlPanelAddServlet", "UserControlPanelEditServlet", "UserControlPanel"})
public class LoggedFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoggedFilter() {
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
		System.out.println("uuuuuuuuuuuuuuuuu au:" + activeUser);
		if (activeUser == null) {
			response.sendRedirect("LogInServlet");
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

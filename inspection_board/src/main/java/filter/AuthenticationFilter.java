package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.HttpUtils;
import model.entity.User;

import static java.util.Objects.isNull;

/**
 * Makes sure that this request comes from a signed in user and the session has not expired.
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private List<String> unProtectedUris = Arrays.asList(
    		"/", 
    		"/login", 
    		"/login_post",
    		"/registration",
    		"/registration_post",
            "/position_in_ratings");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (requestNotRequiresAuthentication(request)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        User currentUser = HttpUtils.getCurrentUserFromFromDb(request);
//TODO not use HARDCODE
        if (isNull(currentUser)) {
            response.sendRedirect("login");
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean requestNotRequiresAuthentication(HttpServletRequest request) {
        return unProtectedUris.contains(request.getPathInfo());
    }

    @Override
    public void destroy() {}
}

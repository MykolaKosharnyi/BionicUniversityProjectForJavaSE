package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import view.JspViewResolver;
import view.ViewResolver;
import model.entity.User;

import static controller.ApplicationResources.*;

/**
 * Checks whether a current user has enough permissions to get a requested resource or perform
 * an operation.
 */
public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final String ACCESS_DENIED_FOR_USER = "Access denied for user '%s' to '%s'!!!%n";
    private ViewResolver viewResolver = JspViewResolver.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Proceeds to the next resource if a current user has enough permissions, and
     * redirects to 'access denied page' otherwise.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isRequestAuthorized(request)) {
            chain.doFilter(request, response);

        } else {
            logAccessDeniedMessage(request);
            response.sendRedirect(viewResolver.resolvePublicViewName(ACCESS_DENIED_PAGE_VIEW_NAME));
        }
    }

    private void logAccessDeniedMessage(HttpServletRequest request) {
        LOGGER.error(String.format(ACCESS_DENIED_FOR_USER,
                getUserNameFromSession(request), request.getRequestURI()));
    }

    private String getUserNameFromSession(HttpServletRequest request) {
        return ((User) request.getSession()
                .getAttribute(CURRENT_USER_ATTR_NAME)).getEmail();
    }

    private boolean isRequestAuthorized(HttpServletRequest request) {
        return Authorization.getInstance().checkPermissions(request);
    }

    @Override
    public void destroy() {
    }
}

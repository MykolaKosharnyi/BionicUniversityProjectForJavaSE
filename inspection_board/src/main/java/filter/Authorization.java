package filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import controller.HttpUtils;
import model.entity.User;

import static controller.ApplicationResources.*;

/**
 * Encapsulates information about resource access permissions of each type of roles.
 */
final class Authorization {
    private static final Map<String, User.Role> permissionMapping = new HashMap<>();

    static {
        User.Role admin = User.Role.ADMIN;

//        permissionMapping.put(GET_ALL_USERS_REQUEST_PATTERN, admin);
//        permissionMapping.put(GET_CREATE_PERIODICAL_REQUEST_PATTERN, admin);
//        permissionMapping.put(GET_UPDATE_PERIODICAL_REQUEST_PATTERN, admin);
//        permissionMapping.put(POST_PERSIST_PERIODICAL_REQUEST_PATTERN, admin);
//        permissionMapping.put(POST_DELETE_PERIODICALS_REQUEST_PATTERN, admin);
//        permissionMapping.put(GET_ADMIN_PANEL_REQUEST_PATTERN, admin);
    }

    private Authorization() {
    }

    private static class InstanceHolder {
        private static final Authorization INSTANCE = new Authorization();
    }

    static Authorization getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Checks whether a current user has enough permissions to access a requested uri
     * using a current http method.
     *
     * @param request a current http request
     * @return {@code true} - if a current user has enough permissions to perform such a kind of requests,
     * and {@code false} otherwise
     */
    boolean checkPermissions(HttpServletRequest request) {

        Optional<Map.Entry<String, User.Role>> accessRestriction = getPermissionMapping(request);

        if (accessRestriction.isPresent()) {
            return isPermissionGranted(accessRestriction.get(), request);
        }

        return true;
    }

    private Optional<Map.Entry<String, User.Role>> getPermissionMapping(HttpServletRequest request) {
        return permissionMapping.entrySet()
                .stream()
                .filter(entry -> filterRequestByUri(request, entry.getKey()))
                .findFirst();
    }
    
    public boolean filterRequestByUri(HttpServletRequest request, String mapping) {
        String urlPattern = mapping.split(METHODS_URI_SEPARATOR)[1];
        String requestUri = request.getPathInfo();

        return Pattern.matches(urlPattern, requestUri);
    }

    private boolean isPermissionGranted(Map.Entry<String, User.Role> permissionMapping,
                                        HttpServletRequest request) {
		User.Role userRole =  getUserFromSession(request).getRole();
        User.Role logitRole = permissionMapping.getValue();

        return logitRole.equals(userRole);
    }

    private User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(CURRENT_USER_ATTR_NAME);
    }
}

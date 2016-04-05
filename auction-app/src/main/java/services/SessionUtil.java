package services;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A utility class to wrap all session data.
 * It holds important stuff such as the username,
 * his privileges and his ID.
 *
 * It also exposes methods to extract values from the
 * session parameters
 *
 * Author: Ido Barash.
 */
@SessionScoped
public class SessionUtil {

    /** Session parameters keys */

    public static final String USER_PHOTO_PATH  = "userPhotoPath";
    public static final String USERNAME         = "username";
    public static final String USER_IS_ADMIN    = "userIsAdmin";
    public static final String USER_ID          = "userId";

    /**
     * Get the full session object.
     *
     * @return HttpSession object
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * Get a parameter value from the session.
     *
     * @param attributeName the parameter to search
     * @return the value if exists.
     */
    public static Object getSessionAttribute(String attributeName) {
        return getSession().getAttribute(attributeName);
    }
}

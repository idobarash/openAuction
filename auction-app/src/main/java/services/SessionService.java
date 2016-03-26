package services;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionService {

    public static final String USER_PHOTO_PATH  = "userPhotoPath";
    public static final String USERNAME         = "username";
    public static final String USER_IS_ADMIN    = "userIsAdmin";


    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static Integer getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (Integer) session.getAttribute("userid");
        }
        else {
            return null;
        }
    }
}

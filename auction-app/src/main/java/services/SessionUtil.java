package services;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionScoped
public class SessionUtil {

    public static final String USER_PHOTO_PATH  = "userPhotoPath";
    public static final String USERNAME         = "username";
    public static final String USER_IS_ADMIN    = "userIsAdmin";
    public static final String USER_ID    = "userId";


    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static Object getSessionAttribute(String attributeName) {
        return getSession().getAttribute(attributeName);
    }


}

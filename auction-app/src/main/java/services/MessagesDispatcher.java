package services;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Utility class displays messages to user.
 */
public class MessagesDispatcher {

    /**
     * Dispatch a message to user
     * @param message the message
     * @param header the box title
     */
    public static void dispatchMessage(String message, String header) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(header,  message) );
    }

}

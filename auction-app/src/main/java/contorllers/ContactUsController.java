package contorllers;

import services.MessagesDispatcher;
import services.NavigationUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class which handles the Contact us view (user.xhtml)
 *
 * Author: Ido Barash
 */
@ViewScoped
@ManagedBean(name = "contactUsController", eager = false)
public class ContactUsController extends BasicController {

    /**
     * Send the contact message
     */
    public void contact() {
        MessagesDispatcher.dispatchMessage("Message sent. We will return to you shortlly", "Thanks");
    }

}

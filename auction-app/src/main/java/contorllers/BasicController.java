package contorllers;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Basic controller class which contains method that are
 * used in all controllers such as request parameters extraction.
 *
 * Author: Ido Barash
 */
public abstract class BasicController {

    /**
     * Get request parameter value
     *
     * @param parameterName the parameter name
     * @return the parameter value or null
     */
    protected String getRequestParameter(String parameterName) {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        return request.getParameter(parameterName);
    }

}

package contorllers;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public abstract class BasicController {

    protected Object getRequestParameter(String parameterName) {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        return request.getParameter(parameterName);
    }

}

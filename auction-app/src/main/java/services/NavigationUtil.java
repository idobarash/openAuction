package services;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class NavigationUtil {

    private static void navigateTo(String path) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.redirect(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateToRoot() {
        navigateTo("/auction-app");
    }
}

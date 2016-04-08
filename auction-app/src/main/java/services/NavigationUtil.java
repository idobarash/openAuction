package services;

import com.sun.org.apache.bcel.internal.generic.ILOAD;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class NavigationUtil {

    public static final String APP_ROOT_PATH = "/auction-app";
    public static final String ITEMS_LIST_PATH = "/auction-app";

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
        navigateTo(APP_ROOT_PATH);
    }

    public static void navigetToItemsPage(String mode, String category, Integer toPage) {

        String parameters = "";
        if (mode != null && mode.isEmpty() == false) {
            parameters += "&mode=" + mode;
        }

        if (category != null && category.isEmpty() == false) {
            parameters += "&category=" + category;
        }

        if (toPage != null) {
            parameters += "&page=" + toPage;
        }

        if (parameters != null) {
            parameters = "?" + parameters.substring(1, parameters.length());
        }

        navigateTo(ITEMS_LIST_PATH + parameters);
    }
}

package services;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class NavigationUtil {

    public static final String APP_ROOT_PATH = "/auction-app";
    public static final String ITEMS_LIST_PATH = "/auction-app?mode=%s&category=%s&page=%d";

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
        navigateTo(String.format(ITEMS_LIST_PATH, mode, category, toPage));
    }
}

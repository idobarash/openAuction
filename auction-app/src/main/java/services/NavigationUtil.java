package services;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Utitlity class to navigate to known URLS with parameters.
 *
 * Author: Ido Barash
 */
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

    /**
     * Navigate to root.
     */
    public static void navigateToRoot() {
        navigateTo(APP_ROOT_PATH);
    }

    /**
     * Navigate to items page with a pagination page.
     * @param mode
     * @param category
     * @param toPage
     */
    public static void navigetToItemsPage(String mode, String category, Integer toPage) {
        navigetToItemsPage(mode, category, toPage, null);
    }

    /**
     * Navigate to items with extra parameters.
     * @param mode
     * @param category
     * @param toPage
     * @param moreParams
     */
    public static void navigetToItemsPage(String mode, String category, Integer toPage, Map<String,String> moreParams) {

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

        if (moreParams != null) {
            for (Map.Entry<String,String> entry : moreParams.entrySet()) {
                parameters += "&" + entry.getKey() + "=" + entry.getValue();
            }
        }

        if (parameters != null) {
            parameters = "?" + parameters.substring(1, parameters.length());
        }

        navigateTo(ITEMS_LIST_PATH + parameters);
    }
}

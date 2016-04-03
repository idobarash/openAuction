package converters;

import contorllers.AppGlobalController;
import contorllers.ItemController;
import entity.ItemCategory;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@ManagedBean(name = "categoryConverterBean",eager = true)
@FacesConverter(value = "categoryConverter")
public class ItemCategoryConverter implements Converter {

    @Inject
    private AppGlobalController appGlobalController;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        for (ItemCategory category : appGlobalController.getCategoriesList()) {
            if (category.getName().equals(s)) {
                return category;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((ItemCategory)o).getName();
    }
}

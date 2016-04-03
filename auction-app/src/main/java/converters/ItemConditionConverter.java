package converters;

import contorllers.AppGlobalController;
import entity.ItemCategory;
import entity.ItemCondition;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@ManagedBean(name = "conditionConverterBean",eager = true)
@FacesConverter(value = "conditionConverter")
public class ItemConditionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        for (ItemCondition condition : ItemCondition.values()) {
            if (condition.getLabel().equals(s)) {
                return condition;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((ItemCondition)o).getLabel();
    }
}

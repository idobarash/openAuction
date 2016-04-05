package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Custom validator to ensure that bid is higher than the current bid
 *
 * Author: Ido Barash
 */
@FacesValidator("validators.BidValidator")
public class BidValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            Integer value = Integer.valueOf(o.toString());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Bid must be a number");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        uiComponent.getAttributes();
    }
}

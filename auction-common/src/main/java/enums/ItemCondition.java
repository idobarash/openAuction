package enums;

/**
 * Item condition descriptor
 * Includes label for display.
 *
 * Author: Ido Barash
 */
public enum ItemCondition {
    BRAND_NEW       ("Brand new"),
    NEW_UNWRAPPED   ("New, but unwrapped"),
    USED_GOOD_STATE ("Used, but in good state"),
    USED_BAD_STATE  ("Used, but in bad state"),
    BROKEN          ("Borken");

    /**
     * The label to display
     */
    private String label;

    ItemCondition(String label) {
        this.label = label;
    }

    /**
     * Get the label to display
     * @return the label to display
     */
    public String getLabel() {
        return label;
    }
}

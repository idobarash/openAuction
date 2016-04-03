package entity;

public enum ItemCondition {
    BRAND_NEW       ("Brand new"),
    NEW_UNWRAPPED   ("New, but unwrapped"),
    USED_GOOD_STATE ("Used, but in good state"),
    USED_BAD_STATE  ("Used, but in bad state"),
    BROKEN          ("Borken");

    private String label;

    ItemCondition(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

package ru.tbcarus.entity.enums;

public enum SpecialFeatures {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    SpecialFeatures(String value) {
        this.value = value;
    }

    public static SpecialFeatures getByValue(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        for (SpecialFeatures feature : SpecialFeatures.values()) {
            if (feature.getValue().equals(value)) {
                return feature;
            }
        }

        return null;
    }

    public String getValue() {
        return this.value;
    }
}

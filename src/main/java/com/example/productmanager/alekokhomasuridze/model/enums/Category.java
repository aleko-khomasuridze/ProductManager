package com.example.productmanager.alekokhomasuridze.model.enums;

public enum Category {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    HOME_APPLIANCES("Home Appliances"),
    BOOKS("Books"),
    GROCERIES("Groceries"),
    TOYS("Toys"),
    SPORTS("Sports"),
    BEAUTY("Beauty");

    private final String displayName;

    // Constructor to set the display name of each category
    Category(String displayName) {
        this.displayName = displayName;
    }

    // Getter method to return the display name
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}

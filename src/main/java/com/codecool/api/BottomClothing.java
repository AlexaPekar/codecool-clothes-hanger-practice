package com.codecool.api;

public class BottomClothing extends Clothing {

    private BottomClothingType type;

    public BottomClothing(String identifier, String brandName, BottomClothingType type) {
        super(identifier, brandName);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Identifier: " + getIdentifier() + "  Brand name: " + getBrandName() + "  Type: " + type;
    }
}

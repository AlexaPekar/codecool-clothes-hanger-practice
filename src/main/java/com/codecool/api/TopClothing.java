package com.codecool.api;

public class TopClothing extends Clothing {

    private TopClothingType type;

    public TopClothing(String identifier, String brandName, TopClothingType type) {
        super(identifier, brandName);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Identifier: " + getIdentifier() + "  Brand name: " + getBrandName() + "  Type: " + type;
    }
}

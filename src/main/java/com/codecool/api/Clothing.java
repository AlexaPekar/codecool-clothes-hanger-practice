package com.codecool.api;

public abstract class Clothing {

    private String identifier;
    private String brandName;

    public Clothing(String identifier, String brandName) {
        this.identifier = identifier;
        this.brandName = brandName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getBrandName() {
        return brandName;
    }

}

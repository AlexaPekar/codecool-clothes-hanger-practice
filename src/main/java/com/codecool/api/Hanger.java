package com.codecool.api;

import com.codecool.exception.HangerIsFullException;
import com.codecool.exception.NoSuchClothingException;

import java.util.ArrayList;
import java.util.List;

public class Hanger {

    private String name;
    private HangerType type;
    private List<Clothing> clothes;


    public Hanger(String name, HangerType type) {
        this.name = name;
        this.type = type;
        this.clothes = new ArrayList<Clothing>();
    }


    public List<Clothing> getClothes() {
        return clothes;
    }

    public String getName() {
        return name;
    }

    public HangerType getType() {
        return type;
    }

    public void addNewClothing(Clothing clothing) throws HangerIsFullException {
        if (hasPlace()) {
            clothes.add(clothing);
        } else {
            throw new HangerIsFullException("\nHanger is full!");
        }
    }

    public void removeClothing(String identifier) throws NoSuchClothingException {
        int count = 0;
        for (Clothing clothing : clothes) {
            if (clothing.getIdentifier().equals(identifier)) {
                clothes.remove(clothing);
                count++;
            }
        }
        if (count == 0) {
            throw new NoSuchClothingException("\nNo such clothing on the hanger!");
        }
    }

    public void removeAllClothes() {
        clothes.clear();
    }

    public boolean hasPlace() {
        switch (type) {
            case ONEPIECE:
                if (clothes.size() < 1) {
                    return true;
                }
                break;
            case TWOPIECE:
                if (clothes.size() < 2) {
                    return true;
                }
                break;
        }
        return false;
    }

}

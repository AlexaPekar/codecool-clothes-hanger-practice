package com.codecool.api;

import com.codecool.exception.ClothesHangerIsEmptyException;
import com.codecool.exception.ClothingHangerIsFullException;
import com.codecool.exception.NoSuchHangerException;

import java.util.ArrayList;
import java.util.List;

public class ClothesHanger {

    private String name;
    private int maxNumOfHangers;
    private List<Hanger> hangers;


    public ClothesHanger(String name, int maxNumOfHangers) {
        this.name = name;
        this.maxNumOfHangers = maxNumOfHangers;
        this.hangers = new ArrayList<Hanger>();
    }

    public String getName() {
        return name;
    }

    public List<Hanger> getHangers() {
            return hangers;
    }

    public void addNewHanger(Hanger hanger) throws ClothingHangerIsFullException {
        if (hasPlace()) {
            hangers.add(hanger);
        } else {
            throw new ClothingHangerIsFullException("\nClothes hanger is full!");
        }
    }

    public void removeHanger(String name) throws NoSuchHangerException {
        hangers.remove(findHangerByName(name));
    }

    public boolean hasPlace() {
        if (hangers.size() < maxNumOfHangers) {
            return true;
        }
        return false;
    }

    public Hanger findHangerByName(String name) throws NoSuchHangerException {
        for (Hanger hanger : hangers) {
            if (hanger.getName().equals(name)) {
                return hanger;
            }
        }
        throw new NoSuchHangerException("\nNo such hanger!");
    }
}

package com.codecool.cmdprog;

import com.codecool.api.*;
import com.codecool.exception.ClothingHangerIsFullException;
import com.codecool.exception.HangerIsFullException;
import com.codecool.exception.NoSuchClothingException;
import com.codecool.exception.NoSuchHangerException;

import java.util.Scanner;

public class CmdProgram {

    Scanner scanner = new Scanner(System.in);
    ClothesHanger myClothesHanger;
    Hanger myHanger;

    public void run() {
        while (myClothesHanger == null) {
            System.out.println("\n\nNo clothes hanger created yet!\n" +
                    "\n(1) Create new clothes hanger" +
                    "\n(0) Exit");
            System.out.println("Enter an option:");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    handleCreateClothesHanger();
                    break;
                case 0:
                    System.out.println("\nSee ya!");
                    System.exit(0);
                default:
                    System.out.println("Wrong input!");
            }
        }
        while (true) {
            while (myHanger == null) {
                System.out.println("\n\nThis is " + myClothesHanger.getName() + " clothes hanger!" +
                        "\n(1) List hangers" +
                        "\n(2) Add new hanger" +
                        "\n(3) Remove hanger" +
                        "\n(4) Choose hanger" +
                        "\n(5) Check place" +
                        "\n(0) Exit");
                System.out.println("Enter an option:");
                int mainInput = Integer.parseInt(scanner.nextLine());
                switch (mainInput) {
                    case 1:
                        handleListHangers();
                        break;
                    case 2:
                        handleAddNewHanger();
                        break;
                    case 3:
                        handleRemoveHanger();
                        break;
                    case 4:
                        handleChooseHanger();
                        break;
                    case 5:
                        handleCheckPlace();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            }
            while (myHanger != null) {
                System.out.println("\n\nThis is " + myHanger.getName() + " hanger!" +
                        "\n(1) Clothes on hanger" +
                        "\n(2) Hang clothing item" +
                        "\n(3) Remove clothing item by identifier" +
                        "\n(4) Clean hanger" +
                        "\n(0) Go back to clothes hanger");
                System.out.println("Enter an option:");
                int subInput = Integer.parseInt(scanner.nextLine());
                switch (subInput) {
                    case 1:
                        handleListClothes();
                        break;
                    case 2:
                        handleHangClothing();
                        break;
                    case 3:
                        handleRemoveClothing();
                        break;
                    case 4:
                        handleRemoveAll();
                        break;
                    case 0:
                        myHanger = null;
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            }
        }
    }

    public void handleCreateClothesHanger() {
        System.out.println("\nEnter the name of the clothes hanger:");
        String name = scanner.nextLine();
        System.out.println("\nEnter the maximum number of hangers on the clothes hanger:");
        try {
            int size = Integer.parseInt(scanner.nextLine());
            myClothesHanger = new ClothesHanger(name, size);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }

    public void handleListHangers() {
        if (myClothesHanger.getHangers().size() > 0) {
            System.out.println("\nThese hangers are on " + myClothesHanger.getName() + " clothes hanger:");
            for (Hanger hanger : myClothesHanger.getHangers()) {
                System.out.println(hanger.getName());
            }
        } else {
            System.out.println("\nClothes hanger is empty!");
        }
    }

    public void handleAddNewHanger() {
        System.out.println("\nEnter the name of the hanger:");
        String name = scanner.nextLine();
        System.out.println("\nEnter the type of the hanger(onepiece/twopiece):");
        String type = scanner.nextLine().toUpperCase();
        try {
            HangerType hangerType = HangerType.valueOf(type);
            myClothesHanger.addNewHanger(new Hanger(name, hangerType));
            System.out.println("\n" + name + " hanger is created!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nNo such hanger type!");
        } catch (ClothingHangerIsFullException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleRemoveHanger() {
        System.out.println("\nEnter the name of the hanger you want to remove:");
        String name = scanner.nextLine();
        try {
            myClothesHanger.removeHanger(name);
            System.out.println("\n" + name + " hanger is removed!");
        } catch (NoSuchHangerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleChooseHanger() {
        System.out.println("\nEnter the name of the hanger:");
        String name = scanner.nextLine();
        try {
            myHanger = myClothesHanger.findHangerByName(name);
        } catch (NoSuchHangerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleCheckPlace() {
        int counter = 0;
        System.out.println("\nEnter the type of clothing item(top/bottom):");
        String type = scanner.nextLine();

        switch (type) {
            case "bottom":
                for (Hanger hanger : myClothesHanger.getHangers()) {
                    if (hanger.getType().equals(HangerType.valueOf("TWOPIECE"))) {
                        if (hanger.hasPlace()) {
                            counter++;
                        }
                    }
                }
                break;
            case "top":
                for (Hanger hanger : myClothesHanger.getHangers()) {
                    if (hanger.hasPlace()) {
                        counter++;
                    }
                }
                break;
            default:
                System.out.println("\nThere's no such type!");
                counter++;
        }
        if (counter > 0) {
            System.out.println("\nThere is " + counter + " empty place for " + type + " clothes!");
        } else {
            System.out.println("\nThere is no empty place for " + type + " clothes!");
        }
    }

    public void handleListClothes() {
        if (myHanger.getClothes().size() > 0) {
            System.out.println("\nClothes on hanger:");
            for (Clothing clothing : myHanger.getClothes()) {
                System.out.println(clothing.toString());
            }
        } else {
            System.out.println("\nHanger is empty!");
        }
    }

    public void handleHangClothing() {
        if (myHanger.hasPlace()) {
            System.out.println("\nEnter the identifier of the clothing item:");
            String identifier = scanner.nextLine();
            System.out.println("\nEnter the brand name of the clothing item:");
            String brandName = scanner.nextLine();
            System.out.println("\nIs it [top] or [bottom] clothing item?");
            String type = scanner.nextLine().toLowerCase();

            if (type.equals("top") && myHanger.getClothes().size() > 0 && myHanger.getClothes().get(0) instanceof TopClothing) {
                System.out.println("\nThere's already top clothing on this hanger!");
            } else if (type.equals("bottom") && myHanger.getClothes().size() > 0 && myHanger.getClothes().get(0) instanceof BottomClothing) {
                System.out.println("\nThere's already bottom clothing on this hanger!");
            } else {
                switch (type) {
                    case "top":
                        System.out.println("\nPlease, enter the clothing item(jacket, blouse, shirt, sweater):");
                        String topClothing = scanner.nextLine().toUpperCase();
                        try {
                            TopClothingType topClothingType = TopClothingType.valueOf(topClothing);
                            myHanger.addNewClothing(new TopClothing(identifier, brandName, topClothingType));
                        } catch (HangerIsFullException e) {
                            System.out.println(e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.out.println("\nNo such clothing product");
                        }
                        System.out.println("\nClothing item hanged on the hanger!");
                        break;
                    case "bottom":
                        if (myHanger.getType().equals(HangerType.valueOf("TWOPIECE"))) {
                            System.out.println("\nPlease, enter the clothing item(trouser, jeans, skirt, shorts):");
                            String bottomClothing = scanner.nextLine().toUpperCase();
                            try {
                                BottomClothingType bottomClothingType = BottomClothingType.valueOf(bottomClothing);
                                myHanger.addNewClothing(new BottomClothing(identifier, brandName, bottomClothingType));
                            } catch (HangerIsFullException e) {
                                System.out.println(e.getMessage());
                            } catch (IllegalArgumentException e) {
                                System.out.println("\nNo such clothing product!");
                            }
                            System.out.println("\nClothing item hanged on the hanger!");
                        } else {
                            System.out.println("\nYou cannot hang bottom clothing on this hanger!");
                        }
                        break;
                    default:
                        System.out.println("\nNo such type!");
                }
            }
        } else {
            System.out.println("\nHanger is full!");
        }
    }

    public void handleRemoveClothing() {
        System.out.println("Enter the identifier of clothing product:");
        String identifier = scanner.nextLine();
        try {
            myHanger.removeClothing(identifier);
            System.out.println("\nClothing item is removed!");
        } catch (NoSuchClothingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleRemoveAll() {
        myHanger.removeAllClothes();
        System.out.println("\nAll clothes are removed!");
    }
}

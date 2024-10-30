package classes;

import java.io.Serializable;

public class Pet extends Animal implements Serializable {
    private String name;
    private  String breed;
    private  String owner_name;
    private boolean microchip;
    private boolean anual_vaccination;

    public Pet() {
    }

    public Pet(String species, int age, String gender, double weight, String name, String breed, String owner_name, boolean microchip, boolean anual_vaccination) {
        super(species, age, gender, weight);
        this.name = name;
        this.breed = breed;
        this.owner_name = owner_name;
        this.microchip = microchip;
        this.anual_vaccination = anual_vaccination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public boolean isMicrochip() {
        return microchip;
    }

    public void setMicrochip(boolean microchip) {
        this.microchip = microchip;
    }

    public boolean isAnual_vaccination() {
        return anual_vaccination;
    }

    public void setAnual_vaccination(boolean anual_vaccination) {
        this.anual_vaccination = anual_vaccination;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", microchip=" + microchip +
                ", anual_vaccination=" + anual_vaccination +
                '}';
    }
}

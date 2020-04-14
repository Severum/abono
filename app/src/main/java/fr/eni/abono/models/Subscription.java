package fr.eni.abono.models;

public class Subscription {
    private float price;
    private float frequency;
    private String name;
    private String description;
    private Priority priority;

    public Subscription(float price, float frequency, String name, String description,
                        Priority priority)
    {
        this.price = price;
        this.frequency = frequency;
        this.name = name;
        this.description = description;
        this.priority = priority;
    }


    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

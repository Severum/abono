package fr.eni.abono.bo;

<<<<<<< HEAD
public class Subscription {

    private long id;
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

    public Subscription(long id, float price, float frequency, String name, String description,
                        Priority priority)
    {
        this.id = id;
        this.price = price;
        this.frequency = frequency;
        this.name = name;
        this.description = description;
        this.priority = priority;
    }
=======
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Subscription {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "frequency")
    private float frequency;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "priority")
    private Priority priority;
>>>>>>> database

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

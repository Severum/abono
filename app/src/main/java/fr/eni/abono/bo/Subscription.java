package fr.eni.abono.bo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

import fr.eni.abono.dao.FrequencyConverter;
import fr.eni.abono.dao.PriorityConverter;

@Entity(foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id",
        childColumns = "category_id",
        onDelete = ForeignKey.NO_ACTION))
public class Subscription implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "frequency")
    @TypeConverters(FrequencyConverter.class)
    private Frequency frequency;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "priority")
    @TypeConverters(PriorityConverter.class)
    private Priority priority;

    @ColumnInfo(name = "category_id")
    private int category_id;

    public Subscription(float price, Frequency frequency, String name, String description, Priority priority, int category_id) {
        this.price = price;
        this.frequency = frequency;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.category_id = category_id;
    }

    public int getId() {
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

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}

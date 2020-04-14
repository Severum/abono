package fr.eni.abono.dao;

import androidx.room.TypeConverter;

import fr.eni.abono.bo.Priority;
import fr.eni.abono.bo.Subscription;

public class PriorityConverter {

    @TypeConverter
    public static Priority toPriority(String label) {
        if (label == Priority.INDISPENSABLE.toString()) {
            return Priority.INDISPENSABLE;
        } else if (label == Priority.IMPORTANT.toString()) {
            return Priority.IMPORTANT;
        } else if (label == Priority.OPTIONAL.toString()) {
            return Priority.OPTIONAL;
        } else {
            throw new IllegalArgumentException("Could not recognize priority");
        }
    }

    @TypeConverter
    public static String toString(Priority priority) {
        return priority.toString();
    }
}
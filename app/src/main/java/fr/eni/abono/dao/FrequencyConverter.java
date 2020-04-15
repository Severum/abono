package fr.eni.abono.dao;

import androidx.room.TypeConverter;

import fr.eni.abono.bo.Frequency;

public class FrequencyConverter {

    @TypeConverter
    public static Frequency toFrequency(float value) {
        if (value == Frequency.DAILY.getValue()) {
            return Frequency.DAILY;
        } else if (value == Frequency.WEEKLY.getValue()) {
            return Frequency.WEEKLY;
        } else if (value == Frequency.MONTHLY.getValue()) {
            return Frequency.MONTHLY;
        } else if (value == Frequency.QUARTERLY.getValue()) {
            return Frequency.QUARTERLY;
        } else if (value == Frequency.SEMESTERLY.getValue()) {
            return Frequency.SEMESTERLY;
        } else if (value == Frequency.ANNUALLY.getValue()) {
            return Frequency.ANNUALLY;
        } else {
            throw new IllegalArgumentException("Could not recognize frequency");
        }
    }

    @TypeConverter
    public static float toFloat(Frequency frequency) {
        return frequency.getValue();
    }
}
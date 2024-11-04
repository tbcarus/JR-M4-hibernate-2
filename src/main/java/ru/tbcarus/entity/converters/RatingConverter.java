package ru.tbcarus.entity.converters;

import jakarta.persistence.AttributeConverter;
import ru.tbcarus.entity.enums.Rating;

public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        for (Rating rating : Rating.values()) {
            if (rating.getValue().equals(s)) {
                return rating;
            }
        }
        return null;
    }
}

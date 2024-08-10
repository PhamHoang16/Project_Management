package com.example.projectmanagement.common.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortRequest {
    @NotNull
    private String direction;
    @NotNull
    private String field;

    public <T> Comparator<T> getComparator(Class<T> clazz) {
        try {
            Field sortField = getField(clazz, field);
            sortField.setAccessible(true);

            Comparator<T> comparator = (o1, o2) -> {
                try {
                    Comparable<Object> value1 = (Comparable<Object>) sortField.get(o1);
                    Comparable<Object> value2 = (Comparable<Object>) sortField.get(o2);
                    if (value1 == null && value2 == null) {
                        return 0;
                    } else if (value1 == null) {
                        return 1;
                    } else if (value2 == null) {
                        return -1;
                    } else {
                        return value1.compareTo(value2);
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error accessing field: " + field, e);
                }
            };

            if ("desc".equalsIgnoreCase(direction)) {
                comparator = comparator.reversed();
            }

            return comparator;
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Invalid sort field: " + field, e);
        }
    }

    private Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getField(superClass, fieldName);
            } else {
                throw e;
            }
        }
    }
}
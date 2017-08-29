package com.acme.edu;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ПавликМорозов {
    public static List<String> стукануть(Object value){
        return Arrays.stream(value.getClass().getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}

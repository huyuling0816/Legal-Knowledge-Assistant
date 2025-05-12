package com.lkms.utils.reflect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AddProperty {

    public static <T> Object addProperty(T object, String propertyName, Object propertyValue){
        ObjectMapper MAPPER = new ObjectMapper();

        Map<String, Object> propertiesMap;
        propertiesMap = new HashMap<>(6);
        propertiesMap.put(propertyName, propertyValue);

        Object obj = null;
        try {
            obj = ReflectUtil.getObject(object, propertiesMap);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
//        try {
//            System.err.println("动态为User添加age之后，User：" + MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        return obj;
    }
}

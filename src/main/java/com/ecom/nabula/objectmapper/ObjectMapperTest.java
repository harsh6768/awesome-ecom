package com.ecom.nabula.objectmapper;

import com.ecom.nabula.objectmapper.pojo.Person;
import com.ecom.nabula.objectmapper.pojo.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
* Object Mapper :  Object mapper helps to convert serilization and de-serrilization of java object
*
* */
public class ObjectMapperTest {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper=new ObjectMapper();

        Root root = new Root();

        root.setCount(2);
        ArrayList<Person> arrayList=new ArrayList<Person>();
        arrayList.add(new Person("Weatherbit","Weather","apiKey",true,"unknown","https://www.weatherbit.io/api","Weather"));
        root.setEntries(arrayList);

        // Convert a Java object to JSON string
        String str= objectMapper.writeValueAsString(root);

        System.out.println("String--------------");
        System.out.println(str);

        // to write value in output stream using writeValue
        objectMapper.writeValue(new File("output.json"), root);

        //convertValue method , will convert one type to another type
        JsonNode jsonNode=objectMapper.convertValue(root,JsonNode.class);


//        jsonNode.get("count")


    }
}

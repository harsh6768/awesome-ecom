package com.ecom.nabula.objectmapper.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Root {
    public int count;
    public ArrayList<Person> entries;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Person> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Person> entries) {
        this.entries = entries;
    }
}



package io.nshusa.meta;

import java.util.*;

public class StoreMeta {

    private final int id;

    private String name;

    public final Map<Integer, String> entries = new TreeMap<>();

    public StoreMeta(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName(int file) {
        return entries.getOrDefault(file, Integer.toString(file));
    }

}

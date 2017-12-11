package io.nshusa.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class StoreWrapper {

    private ObservableValue<Integer> id;

    private SimpleStringProperty name;

    public StoreWrapper(int id, String name) {
        this.id = new SimpleIntegerProperty(id).asObject();
        this.name = new SimpleStringProperty(name == null || name.isEmpty() ? "unknown" : name);
    }

    public ObservableValue<Integer> idProperty() {
        return id;
    }

    public int getId() {
        return id.getValue();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
}

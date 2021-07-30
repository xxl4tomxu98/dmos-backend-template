package com.dmos.backend.lifecycle;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class ChangedProperty implements Serializable {

    @NotNull
    private String propertyName;
    private Object oldValue;
    private Object newValue;

    public ChangedProperty() {
    }

    public ChangedProperty(@NotNull String propertyName, Object oldValue, Object newValue) {
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangedProperty that = (ChangedProperty) o;
        return propertyName.equals(that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyName);
    }
}


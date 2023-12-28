package com.example.custom_annotation;

import java.io.Serializable;

public class WeakEntity<K extends Serializable> {
    K id;

    protected WeakEntity() {
    }

    public WeakEntity(K id) {
        this.id = id;
    }

    public K getId() {
        return id;
    }
}

package com.example.customtelinkapp.model;

import java.io.Serializable;

public class MeshAppKey implements MeshKey, Serializable {
    public String name;
    public int index;
    public byte[] key;
    public int boundNetKeyIndex;

    public MeshAppKey(String name, int index, byte[] key, int boundNetKeyIndex) {
        this.name = name;
        this.index = index;
        this.key = key;
        this.boundNetKeyIndex = boundNetKeyIndex;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public byte[] getKey() {
        return this.key;
    }
}

package com.example.slsmettle.domain.model;

import lombok.Data;

public enum Type {

    hockey_pads,
    hockey_skates,
    hockey_stick;

    Type() {
    }

    public static Type of(String type) {
        try {
            return Type.valueOf(type);
        } catch (Exception ex) {
            throw new RuntimeException(String.format("type: %s does not exist", type));
        }

    }
}

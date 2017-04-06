package com.teguh.jpa.crud.example.domain.types;

public enum Gender {
    L("Laki-Laki"),
    P("Perempuan");

    private final String label;

    Gender(String name) {
        this.label = name;
    }

    public String getLabel() {
        return label;
    }
}

package com.example.MY21;

public class LanguageOption {
    private String code;
    private String name;

    public LanguageOption(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name;
    }
}
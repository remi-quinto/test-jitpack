package com.example.mylibrary;

import androidx.annotation.NonNull;

public class PublicClass {
    public String publicProperty;
    private String privateProperty;

    /**
     * This is public method
     */
    public void publicMethod(@NonNull String param) {}

    /**
     * This is public method
     */
    public void publicMethod2(String param) {}
    private void privateMethod() {}
}

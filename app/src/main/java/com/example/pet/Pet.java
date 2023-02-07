package com.example.pet;

import java.util.List;

public class Pet {

    private String name;


    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    private List<String> photoUrls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

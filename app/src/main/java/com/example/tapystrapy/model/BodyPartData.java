package com.example.tapystrapy.model;

public class BodyPartData {
    public String bodyPart, bodyPartPolish;
    public Gender gender;

    public BodyPartData(String bodyPart, String bodyPartPolish, Gender gender) {
        this.bodyPart = bodyPart;
        this.bodyPartPolish = bodyPartPolish; // must be singular when deciding later if left or right
        this.gender = gender;
    }
}
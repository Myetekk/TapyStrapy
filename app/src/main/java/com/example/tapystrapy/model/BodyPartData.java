package com.example.tapystrapy.model;

public class BodyPartData {
    public String bodyPart, bodyPartPolish;
    public Gender gender;

    public BodyPartData(String bodyPart, String bodyPartPolish, Gender gender) {
        this.bodyPart = bodyPart;
        this.bodyPartPolish = bodyPartPolish;
        this.gender = gender;
    }
}
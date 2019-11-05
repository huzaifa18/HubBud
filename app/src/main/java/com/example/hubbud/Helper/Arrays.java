package com.example.hubbud.Helper;

import com.example.hubbud.Model.DataIds;

import java.util.ArrayList;

public class Arrays {

    public static ArrayList<DataIds> ArtsCrafts = new ArrayList<DataIds>(){{
        add(new DataIds("Artcrafts","0"));
        add(new DataIds("Calligraphy/Embroidery","1"));
        add(new DataIds("Cooking","2"));
        add(new DataIds("Dancing","3"));
        add(new DataIds("Drawing/Painting","4"));
        add(new DataIds("Graffiti","5"));
        add(new DataIds("Photography","6"));
        add(new DataIds("Music/Singing","7"));
        add(new DataIds("Language/Translation","8"));
        add(new DataIds("Pottery","9"));
        add(new DataIds("Teaching/Coaching","10"));
        add(new DataIds("Jewelery Crafting","11"));
        add(new DataIds("Paper Craft","12"));
        add(new DataIds("Baby Sitting","13"));
        add(new DataIds("Pet Sitter","14"));
        add(new DataIds("Gardening","15"));
    }};

    public static ArrayList<DataIds> Sports = new ArrayList<DataIds>(){{
        add(new DataIds("Football/Futsal","0"));
        add(new DataIds("Cricket","1"));
        add(new DataIds("Badminton","2"));
        add(new DataIds("Table Tennis","3"));
        add(new DataIds("Tennis","4"));
        add(new DataIds("Squash","5"));
        add(new DataIds("Hockey","6"));
        add(new DataIds("Basketball","7"));
        add(new DataIds("Chess","8"));
        add(new DataIds("Gymnastic","9"));
        add(new DataIds("Martial Arts","10"));
        add(new DataIds("Motor Sports","11"));
        add(new DataIds("Swimming","12"));
        add(new DataIds("Skiing","13"));
        add(new DataIds("E-Gaming","14"));
    }};

    public static ArrayList<DataIds> Tourism = new ArrayList<DataIds>(){{
        add(new DataIds("City Tour","0"));
        add(new DataIds("Museum Tour","1"));
        add(new DataIds("Northern Area Tour","2"));
        add(new DataIds("Cultural Tour","3"));
        add(new DataIds("Customize Tour","4"));
    }};

}
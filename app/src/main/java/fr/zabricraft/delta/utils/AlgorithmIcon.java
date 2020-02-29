package fr.zabricraft.delta.utils;

import org.json.JSONObject;

import java.io.Serializable;

public class AlgorithmIcon implements Serializable {

    public static final String defaultIcon = "x";
    public static final String defaultColor = "river";

    private String icon;
    private String color;

    public AlgorithmIcon(String icon, String color) {
        this.icon = icon;
        this.color = color;
    }

    public AlgorithmIcon() {
        this(defaultIcon, defaultColor);
    }

    public AlgorithmIcon(JSONObject object) {
        try {
            this.icon = object.has("icon") ? object.getString("icon") : null;
            this.color = object.has("color") ? object.getString("color") : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
package site.rakshith.myapplication.pojo;

import android.graphics.drawable.Drawable;

public class OnboardItem {


    private int image;
    private String header;
    private String description;
    private int Color;

    public OnboardItem(int image, String header, String description, int color) {
        this.image = image;
        this.header = header;
        this.description = description;
        Color = color;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package com.example.javacolor.model;

public class ColorModel {

    private int red;
    private int green;
    private int blue;
    private String hexValue;

    public ColorModel(int red, int green, int blue) {
        valideRGB(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hexValue = convertRGBToHex(red, green, blue);

    }

    public ColorModel(String hexValue) {
        validateHexValue(hexValue);
        this.hexValue = hexValue;
        int[] rgb = convertHexToRGB(hexValue);
        this.red = rgb[0];
        this.green = rgb[1];
        this.blue = rgb[2];
    }

    @Override
    public String toString() {
        return String.format("[value=%s, r=%d, g=%d, b=%d]", hexValue, red, green, blue);
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        validateHexValue(hexValue);
        int[] rgb = convertHexToRGB(hexValue);
        this.red = rgb[0];
        this.green = rgb[1];
        this.blue = rgb[2];
        this.hexValue = hexValue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        valideRGB(red, this.green, this.blue);
        this.red = red;
        this.hexValue = convertRGBToHex(red, this.green, this.blue);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        valideRGB(this.red, green, this.blue);
        this.green = green;
        this.hexValue = convertRGBToHex(this.red, green, this.blue);
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        valideRGB(this.red, this.green, blue);
        this.blue = blue;
        this.hexValue = convertRGBToHex(this.red, this.green, blue);
    }

    private void valideRGB(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException("Valeurs RGB invalides");
        }
    }

    private String convertRGBToHex(int red, int green, int blue) {
        return String.format("#%02X%02X%02X", red, green, blue);
    }

    private int[] convertHexToRGB(String hexValue) {
        int red = Integer.parseInt(hexValue.substring(1, 3), 16);
        int green = Integer.parseInt(hexValue.substring(3, 5), 16);
        int blue = Integer.parseInt(hexValue.substring(5, 7), 16);
        return new int[]{red, green, blue};
    }

    private void validateHexValue(String hexValue) {
        if (hexValue == null || !hexValue.matches("^#[0-9A-F]{6}$")) {
            throw new IllegalArgumentException("Format hexadécimal invalide");
        }
    }
}

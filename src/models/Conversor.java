package models;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Conversor {
    private double ars;
    private double bob;
    private double brl;

    public Conversor(String jsonResponse) {
        JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject conversionRates = root.getAsJsonObject("conversion_rates");

        this.ars = conversionRates.get("ARS").getAsDouble();
        this.bob = conversionRates.get("BOB").getAsDouble();
        this.brl = conversionRates.get("BRL").getAsDouble();
    }

    public double getArs() {
        return this.ars;
    }

    public double getBob() {
        return this.bob;
    }

    public double getBrl() {
        return this.brl;
    }
}
package br.com.dto;


public class TemperatureDto {
    private String city;
    private String region;
    private String country;
    private int temperature;
    private int feelsLike;
    private String timeZone;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(int feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "Cidade: "+ this.city +
        "\nEstado: "+ this.region +
        "\nPais:" + this.country +
        "\nTemperatura: "+ this.temperature +"º"+
        "\nSencação Térmica: "+ this.feelsLike +"º"+
        "\nFuso Horário: "+ this.timeZone;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author khuyenn
 */
public class Data {

    private String id;
    private String crime;
    private String number;
    private String date;
    private String location;
    private String beat;
    private String neighborhood;
    private String npu;
    private String lat;
    private String longtitude;

    public Data() {
    }

    public Data(String id, String crime, String number, String date, String location, String beat, String neighborhood, String npu, String lat, String longtitude) {
        this.id = id;
        this.crime = crime;
        this.number = number;
        this.date = date;
        this.location = location;
        this.beat = beat;
        this.neighborhood = neighborhood;
        this.npu = npu;
        this.lat = lat;
        this.longtitude = longtitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBeat() {
        return beat;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNpu() {
        return npu;
    }

    public void setNpu(String npu) {
        this.npu = npu;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
    
}

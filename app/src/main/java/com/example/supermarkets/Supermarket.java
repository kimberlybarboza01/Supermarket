package com.example.supermarkets;

public class Supermarket {
    private int supermarketID;
    private String SupermarketName;
    private String StreetAddress;
    private String City;
    private String State;
    private String ZipCode;
    private String LiquorRate;
    private String ProduceRate;
    private String MeatRate;
    private String CheeseRate;
    private String CheckoutRate;

    public Supermarket (){
        supermarketID = -1;
    }

    public int getSupermarketID(){
        return supermarketID;
    }
    public void setSupermarketID(int i){
        supermarketID = i;
    }
    public String getSupermarketName(){
        return SupermarketName;
    }
    public void setSupermarketName(String s){
        SupermarketName = s;
    }
    public String getStreetAddress(){
        return StreetAddress;
    }
    public void setStreetAddress(String s){
        StreetAddress = s;
    }
    public String getCity(){
        return City;
    }
    public void setCity(String s){
        City = s;
    }
    public String getState(){
        return State;
    }
    public void setState(String s){
        State = s;
    }
    public String getZipCode(){
        return ZipCode;
    }
    public void setZipCode(String s){
        ZipCode = s;
    }
    public String getLiquorRate(){
        return LiquorRate;
    }
    public void setLiquorRate(String s){
        LiquorRate = s;
    }
    public String getProduceRate(){
        return ProduceRate;
    }
    public void setProduceRate(String s){
        ProduceRate = s;
    }
    public String getMeatRate(){
        return MeatRate;
    }
    public void setMeatRate(String s){
        MeatRate = s;
    }
    public String getCheeseRate(){
        return CheeseRate;
    }
    public void setCheeseRate(String s){
        CheeseRate = s;
    }
    public String getCheckoutRate(){
        return CheckoutRate;
    }
    public void setCheckoutRate(String s){
        CheckoutRate = s;
    }
}

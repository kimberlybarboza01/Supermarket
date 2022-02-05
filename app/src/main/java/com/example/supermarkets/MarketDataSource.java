package com.example.supermarkets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MarketDataSource {

    private SQLiteDatabase database;
    private MarketDBHelper dbHelper;

    public MarketDataSource(Context context){
        dbHelper = new MarketDBHelper(context);
    }
    public void open () throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close (){
        dbHelper.close();
    }
    public boolean insertSupermarket(Supermarket m) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("supermarketname", m.getSupermarketName());
            initialValues.put("streetaddress", m.getStreetAddress());
            initialValues.put("city", m.getCity());
            initialValues.put("state", m.getState());
            initialValues.put("zipcode", m.getZipCode());
            initialValues.put("liquorrate", m.getLiquorRate());
            initialValues.put("producerate", m.getProduceRate());
            initialValues.put("meatrate", m.getMeatRate());
            initialValues.put("cheeserate", m.getCheeseRate());
            initialValues.put("checkoutrate", m.getCheckoutRate());

            didSucceed = database.insert("supermarket", null, initialValues)>0;
        }
        catch (Exception e){
            //do nothing - will return false if there is an exceptions
        }
        return didSucceed;
    }

    public boolean updateSupermarket(Supermarket m){
        boolean didSucceed = false;
        try {
            Long rowId = (long) m.getSupermarketID();
            ContentValues updateValues = new ContentValues();
            updateValues.put("supermarketname", m.getSupermarketName());
            updateValues.put("streetaddress", m.getStreetAddress());
            updateValues.put("city", m.getCity());
            updateValues.put("state", m.getState());
            updateValues.put("zipcode", m.getZipCode());
            updateValues.put("liquorrate", m.getLiquorRate());
            updateValues.put("producerate", m.getProduceRate());
            updateValues.put("meatrate", m.getMeatRate());
            updateValues.put("cheeserate", m.getCheeseRate());
            updateValues.put("checkoutrate", m.getCheckoutRate());

            didSucceed = database.update("supermarket", updateValues, "_id=" + rowId, null)>0;
        }
        catch (Exception e){
        }
        return didSucceed;
    }

    public int getLastMarketID (){
        int lastId;
        try {
            String query = "Select MAX(_id) from supermarket";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId= cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e){
            lastId = -1;
        }
        return lastId;
    }

}

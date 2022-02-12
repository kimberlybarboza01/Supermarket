package com.example.supermarkets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class MarketDataSource {
    private SQLiteDatabase database;
    private MarketDBHelper dbHelper;

    public MarketDataSource(Context context) {
        dbHelper = new MarketDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertSupermarket(Supermarket c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("supermarketname", c.getSupermarketName());
            initialValues.put("street-address", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipCode());
            initialValues.put("liquor-rate", c.getLiquorRate());
            initialValues.put("producerate", c.getProduceRate());
            initialValues.put("meatrate", c.getMeatRate());
            initialValues.put("cheeserate", c.getCheeseRate());
            initialValues.put("checkoutrate", c.getCheckoutRate());
            initialValues.put("averagerate", c.getAverageRate());





            didSucceed = database.insert("Supermarket", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateSupermarket(Supermarket c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getSupermarketID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("supermarketname", c.getSupermarketName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipCode());
            updateValues.put("liquorrate", c.getLiquorRate());
            updateValues.put("producerate", c.getProduceRate());
            updateValues.put("meatrate", c.getMeatRate());
            updateValues.put("cheeserate", c.getCheeseRate());
            updateValues.put("checkoutrate", c.getCheckoutRate());
           // updateValues.put("averagerate", c.getAverageRate());




            didSucceed = database.update("supermarket", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public int getLastSupermarketId() {
        int lastId;
        try {
            String query = "Select MAX(_id) from supermarket";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public ArrayList<String> getSupermarketName() {
        ArrayList<String> supermarketNames = new ArrayList<>();
        try {
            String query = "Select supermarketname from supermarket";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                supermarketNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            supermarketNames = new ArrayList<String>();
        }
        return supermarketNames;
    }

    public ArrayList<Supermarket> getSupermarkets(String sortField, String sortOrder) {
        ArrayList<Supermarket> supermarket = new ArrayList<Supermarket>();
        try {
            String query = "SELECT  * FROM supermarket ORDER BY " + sortField + " " + sortOrder;
            Cursor cursor = database.rawQuery(query, null);

            Supermarket newSupermarket;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newSupermarket = new Supermarket();
                newSupermarket.setSupermarketID(cursor.getInt(0));
                newSupermarket.setSupermarketName(cursor.getString(1));
                newSupermarket.setStreetAddress(cursor.getString(2));
                newSupermarket.setCity(cursor.getString(3));
                newSupermarket.setState(cursor.getString(4));
                newSupermarket.setZipCode(cursor.getString(5));
                newSupermarket.setLiquorRate(cursor.getString(6));
                newSupermarket.setProduceRate(cursor.getString(7));
                newSupermarket.setMeatRate(cursor.getString(8));
                newSupermarket.setCheeseRate(cursor.getString(9));
                newSupermarket.setCheckoutRate(cursor.getString(10));
                //newSupermarket.setAverageRate(cursor.getString(11));
                supermarkets.add(newSupermarket);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            supermarkets = new ArrayList<Supermarket>();
        }
        return supermarkets;
    }

    public Supermarket getSpecificSupermarket(int supermarketId) {
        Supermarket supermarket = new Supermarket();
        String query = "SELECT  * FROM supermarket WHERE _id =" + supermarketId;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            supermarket.setSupermarketID(cursor.getInt(0));
            supermarket.setSupermarketName(cursor.getString(1));
            supermarket.setStreetAddress(cursor.getString(2));
            supermarket.setCity(cursor.getString(3));
            supermarket.setState(cursor.getString(4));
            supermarket.setZipCode(cursor.getString(5));
            supermarket.setLiquorRate(cursor.getString(6));
            supermarket.setProduceRate(cursor.getString(7));
            supermarket.setMeatRate(cursor.getString(8));
            supermarket.setCheeseRate(cursor.getString(9));
            supermarket.setCheckoutRate(cursor.getString(10));
            //supermarket.setAverageRate(cursor.getString(11));

            cursor.close();
        }
        return supermarket;
    }

    public boolean deleteSupermarket(int supermarketId) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("supermarket", "_id=" + supermarketId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -return value already set to false
        }
        return didDelete;
    }

}
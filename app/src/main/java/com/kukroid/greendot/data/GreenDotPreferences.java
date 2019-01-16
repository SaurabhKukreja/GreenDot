package com.kukroid.greendot.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kukroid.greendot.model.FIBSeries;

/**
 * Created by kukresa on 1/15/2019.
 */

public class GreenDotPreferences {

    private static GreenDotPreferences greenDotPreferences;
    private SharedPreferences preferences = null;
    private String PREFERENCES = "GreenDot";
    SharedPreferences.Editor editor = null;
    private static final String SIZE = "size";
    private String TAG_FIB = ".fib";


    private GreenDotPreferences(Context ctx){
        preferences = ctx.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

    }

    public static GreenDotPreferences getInstance(Context ctx){
        if (greenDotPreferences == null){
            greenDotPreferences = new GreenDotPreferences(ctx);
        }
        return greenDotPreferences;
    }

    public boolean saveRecord(FIBSeries fib) {
        try {
            int size = preferences.getInt(SIZE, 0);
            editor = preferences.edit();
            if(fib != null ) {
                String fibGson = (new Gson()).toJson(fib);
                editor.putString(String.valueOf(size) + TAG_FIB, fibGson);
            }
            size++;
            editor.putInt(SIZE, size);
            return editor.commit();
        } catch (NullPointerException e){

            return false;
        }  catch (Exception e){

            return false;
        }
    }

    public int getSize(){
        return preferences.getInt(SIZE, 0);
    }

    public FIBSeries getFIBRecord(int index) {
        FIBSeries dto = null;
        try {
            dto = (new Gson()).fromJson(
                    preferences.getString(String.valueOf(index) + TAG_FIB, ""), FIBSeries.class);
        } catch (NullPointerException e){

        } catch (JsonSyntaxException e){

        }catch (Exception e ){

        }
        return dto;
    }


}

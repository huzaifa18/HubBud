package com.example.hubbud.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Prefs {

    public static void addingUserUDID(Context context, final String userUDID){
        SharedPreferences userUDIDPref = context.getSharedPreferences("hubbud_user", 0);
        SharedPreferences.Editor editor = userUDIDPref.edit();
        editor.putString("user_udid", userUDID);
        editor.commit();

    }

    public static void addingHobby1(Context context, final String hobby, final String sub){
        SharedPreferences userUDIDPref = context.getSharedPreferences("hubbud", 0);
        SharedPreferences.Editor editor = userUDIDPref.edit();
        editor.putString("hobby1", hobby);
        editor.putString("hobby1sub", sub);
        editor.commit();

    }

    public static void addingHobby2(Context context, final String hobby, final String sub){
        SharedPreferences userUDIDPref = context.getSharedPreferences("hubbud", 0);
        SharedPreferences.Editor editor = userUDIDPref.edit();
        editor.putString("hobby2", hobby);
        editor.putString("hobby2sub", sub);
        editor.commit();

    }

    public static void addingHobby3(Context context, final String hobby, final String sub){
        SharedPreferences userUDIDPref = context.getSharedPreferences("hubbud", 0);
        SharedPreferences.Editor editor = userUDIDPref.edit();
        editor.putString("hobby3", hobby);
        editor.putString("hobby3sub", sub);
        editor.commit();

    }

    public static String gettUserUDID(Context context){

        SharedPreferences userUDIDPref = context.getSharedPreferences("hubbud_user", 0);
        String UDID = userUDIDPref.getString("udid", "-1");
        return UDID;
    }

    public static void settUserUDID(Context context, String token){

        SharedPreferences userUDIDPref = context.getSharedPreferences("hubbud_user", 0);
        SharedPreferences userLoginPref  = context.getSharedPreferences("hubbud_user", 0);
        SharedPreferences.Editor editor = userLoginPref.edit();
        editor.putString("udid", token);
        editor.commit();

    }

    public static void addPrefsForLogin(Context context, final String user_id,
                                        final String name,final String about, final String location,  final String username,
                                        final String  email, final String dob, final String gender,final String phone,final String password){

        SharedPreferences userLoginPref  = context.getSharedPreferences("hubbud_user", 0);
        SharedPreferences.Editor editor = userLoginPref.edit();
        editor.putString("user_id", user_id);
        editor.putString("name", name);
        editor.putString("about", about);
        editor.putString("location", location);
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("dob", dob);
        editor.putString("gender", gender);
        editor.putString("phone", phone);
        editor.putString("password", password);
        editor.commit();

    }

    //getting user id
    public static String getUserIDFromPref(Context context){
        SharedPreferences userLoginPref  = context.getSharedPreferences("hubbud_user", 0);
        String userId = userLoginPref.getString("user_id", "");
        return userId;
    }

    //gettitng name
    public static String getUserFullNameFromPref(Context context){
        SharedPreferences preUserFullName  = context.getSharedPreferences("hubbud_user", 0);
        String fullName = preUserFullName.getString("name", "-1");
        return fullName;
    }

    //gettitng about
    public static String getUseraboutFromPref(Context context){
        SharedPreferences preUserName  = context.getSharedPreferences("hubbud_user", 0);
        String username = preUserName.getString("about", "-1");
        return username;
    }

    //gettitng phone
    public static String getPhoneFromPref(Context context){
        SharedPreferences preUserName  = context.getSharedPreferences("hubbud_user", 0);
        String phone = preUserName.getString("phone", "-1");
        return phone;
    }

    //gettitng location
    public static String getUserlocationFromPref(Context context){
        SharedPreferences preUserName  = context.getSharedPreferences("hubbud_user", 0);
        String username = preUserName.getString("location", "-1");
        return username;
    }

    //gettitng user name
    public static String getUserNameFromPref(Context context){
        SharedPreferences preUserName  = context.getSharedPreferences("hubbud_user", 0);
        String username = preUserName.getString("username", "-1");
        return username;
    }

    //gettitng user name
    public static String getEmailFromPref(Context context){
        SharedPreferences preUserEmail  = context.getSharedPreferences("hubbud_user", 0);
        String email = preUserEmail.getString("email", "-1");
        return email;
    }

    //getting user id
    public static String getUserdobFromPref(Context context){
        SharedPreferences userLoginPref  = context.getSharedPreferences("hubbud_user", 0);
        String dob = userLoginPref.getString("dob", "");
        return dob;
    }

    //gettitng user gender
    public static String getUsergenderFromPref(Context context){
        SharedPreferences usergenderPref  = context.getSharedPreferences("hubbud_user", 0);
        String userId = usergenderPref.getString("gender", "-1");
        return userId;
    }

    //getting password
    public static String getLocationFromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud_user", 0);
        String location = prepassword.getString("location", "-1");
        return location;
    }

    //getting password
    public static String getPasswordFromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud_user", 0);
        String password = prepassword.getString("password", "-1");
        return password;
    }

    //getting hobby1
    public static String getHobby1FromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby1", "-1");
        return password;
    }

    //getting hobby2
    public static String getHobby2FromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby2", "-1");
        return password;
    }

    //getting hobby3
    public static String getHobby3FromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby3", "-1");
        return password;
    }

    //getting hobby1
    public static String getHobby1subFromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby1sub", "-1");
        return password;
    }

    //getting hobby2
    public static String getHobby2subFromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby2sub", "-1");
        return password;
    }

    //getting hobby3
    public static String getHobby3subFromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby3sub", "-1");
        return password;
    }

    //getting hobby1
    public static void delHobby1FromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        SharedPreferences.Editor editor = prepassword.edit();
    }

    //getting hobby2
    public static void delHobby2FromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby2sub", "-1");
    }

    //getting hobby3
    public static void delHobby3FromPref(Context context){
        SharedPreferences prepassword  = context.getSharedPreferences("hubbud", 0);
        String password = prepassword.getString("hobby3sub", "-1");
    }


    public static ArrayList<String> getAllUserValueFromPref(Context context){

        ArrayList<String> arrayList = new ArrayList<>();
        SharedPreferences userLoginPref  = context.getSharedPreferences("hubbud_user", 0);
        String userId = userLoginPref.getString("user_id", "");
        String name = userLoginPref.getString("name", "");
        String username = userLoginPref.getString("username", "");
        String email = userLoginPref.getString("email", "");
        String dob = userLoginPref.getString("dob", "");
        String password = userLoginPref.getString("password", "");
        String gender = userLoginPref.getString("gender", "");
        arrayList.add(userId);
        arrayList.add(name);
        arrayList.add(username);
        arrayList.add(email);
        arrayList.add(dob);
        arrayList.add(password);
        arrayList.add(gender);

        return arrayList;
    }

    public static void clearPrefData(Context context){
        SharedPreferences userLoginPref  = context.getSharedPreferences("hubbud_user", 0);
        SharedPreferences.Editor editor = userLoginPref.edit();
        editor.clear();
        editor.commit();
    }
}
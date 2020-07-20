package com.technomicron.qc.gamedata;

import android.content.Context;
import android.content.SharedPreferences;

import android.content.SharedPreferences.Editor;

public class Shared_Prefs {

	public static SharedPreferences getSharedPref(Context mContext) {
		SharedPreferences pref = mContext.getSharedPreferences("qcShPrefs", Context.MODE_PRIVATE);

		return pref;
	}

	public static void setString(Context mContext, String key, String value) {
		if(key!=null){
			Editor edit = getSharedPref(mContext).edit();
			edit.putString(key, value);
			edit.commit();
		}
	}

	public static void setInt(Context mContext, String key, int value) {
		if(key!=null){
			Editor edit = getSharedPref(mContext).edit();
			edit.putInt(key, value);
			edit.commit();
		}
	}

	public static void setLong(Context mContext, String key, Long value) {
		if(key!=null){
			Editor edit = getSharedPref(mContext).edit();
			edit.putLong(key, value);
			edit.commit();
		}
	}

	public static void setBoolean(Context mContext, String key, boolean value) {
		if(key!=null){
			Editor edit = getSharedPref(mContext).edit();
			edit.putBoolean(key, value);
			edit.commit();
		}
	}

	public static void removePref(Context mContext, String key) {
		if(key!=null){
			Editor edit = getSharedPref(mContext).edit();
			edit.remove(key);
			edit.commit();
		}
	}

	public static boolean containsPref(Context mContext, String key) {
		boolean isContained = false;
		if(key!=null){
			if(getSharedPref(mContext).contains(key)){
				isContained = true;
			}
		}
		return isContained;
	}


	public static String getString(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		String val = "";
		try {
			if (pref.contains(key))
				val = pref.getString(key, "");
			else
				val = "";
		}catch (Exception e){
			e.printStackTrace();
		}
		return val;
	}

	public static int getInt(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		int val = 0;
		try {
			if(pref.contains(key)) val = pref.getInt(key, 0);
		}catch (Exception e){
			e.printStackTrace();
		}
		return val;
	}

	public static Long getLong(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		Long val = null;
		try{
			if(pref.contains(key)) val = pref.getLong(key, 0);
		}catch (Exception e){
			e.printStackTrace();
		}
		return val;
	}

	public static boolean getBoolean(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		boolean val = false;
		try{
			if(pref.contains(key)) val = pref.getBoolean(key, false);

		}catch (Exception e){
			e.printStackTrace();
		}
		return val;
	}


	public static boolean containkey(Context mContext,String key)
	{
		SharedPreferences pref = getSharedPref(mContext);
		return pref.contains(key);
	}

	public static void saveTestScreens(Context mContext, String key,
	                                   String value) {
		Editor edit = getSharedPref(mContext).edit();
		edit.putString(key, value);
		edit.commit();
	}

	public static String getTestScreens(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		String val = "";
		if (pref.contains(key))
			val = pref.getString(key, "");
		else
			val = "";
		return val;
	}

	public static void removeAllPrefs(Context mContext){
		SharedPreferences pref = mContext.getSharedPreferences("qcShPrefs", Context.MODE_PRIVATE);
		pref.edit().clear().commit();
	}
}

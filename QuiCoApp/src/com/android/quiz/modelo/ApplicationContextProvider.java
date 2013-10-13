package com.android.quiz.modelo;

import com.android.quiz.db.DBHelper;

import android.app.Application;
import android.content.Context;

public class ApplicationContextProvider extends Application {
	
	 /**
     * Keeps a reference of the application context
     */
    private static Context sContext;
 
    private static DBHelper db;
    
    @Override
    public void onCreate() {
        super.onCreate();
 
        sContext = getApplicationContext();
        db = new DBHelper(getContext());
    }
 
    /**
     * Returns the application context
     *
     * @return application context
     */
    public static Context getContext() {
        return sContext;
    }
    
    public static DBHelper getBD(){
    	return db;
    }

}

#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

#parse("File Header.java")
public class ${NAME} {

    public static String PREFS_NAME = "project_name_prefs"; // TODO: Change name

    // TODO: Add your properties in Shared Preferences
    
    private SharedPreferences mSharedPrefs;
    private SharedPreferences.Editor mEditor;
    
    @Inject public ${NAME}(Context context) {
        this.mSharedPrefs = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        this.mEditor = mSharedPrefs.edit();
    }
    
    // TODO: Add methods to get, set your properties
    
    public boolean clear() {
        this.mEditor.clear();
        return this.mEditor.commit();
    }
    
    
}
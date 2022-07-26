package com.hmsecosystem.calculator;

import static com.hmsecosystem.calculator.AttributionAppsFlyer.createAttributionAppsFlyer;


import android.content.res.Resources;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import java.util.Map;

public class App extends MultiDexApplication {
    private static Resources resources;
    static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();
        final String AF_DEV_KEY = resources.getString(R.string.AF_DEV_KEY);

    

//        set single Dashboard third party store name here:
//        The value set here appears in AppsFlyer raw data install_app_store field and in
//        Overview dashboard
//        Cohort dashboard
//        Raw data reports (Raw data reports are an AppsFlyer premium feature)
        AppsFlyerLib.getInstance().setOutOfStore("AG_Connect");

        AttributionAppsFlyer attr = createAttributionAppsFlyer(this);
        attr.TrackEvent();


        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener(){
            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    Log.d(TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
                setInstallData(conversionData);
            }

            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d(TAG, "error getting conversion data: " + errorMessage);
            }

            /* Called only when a Deep Link is opened */
            @Override
            public void onAppOpenAttribution(Map<String, String> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    Log.d(TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d(TAG, "error onAttributionFailure : " + errorMessage);
            }
        };

        /* This API enables AppsFlyer to detect installations, sessions, and updates. */
        AppsFlyerLib.getInstance().init(AF_DEV_KEY , conversionListener , getApplicationContext());
        AppsFlyerLib.getInstance().start(this);

    }


    /* IGNORE - USED TO DISPLAY INSTALL DATA */
    public static String InstallConversionData =  "";
    public static int sessionCount = 0;
    public static void setInstallData(Map<String, Object> conversionData){
        if(sessionCount == 0){
            final String install_type = "Install Type: " + conversionData.get("af_status") + "\n";
            final String media_source = "Media Source: " + conversionData.get("media_source") + "\n";
            final String install_time = "Install Time(GMT): " + conversionData.get("install_time") + "\n";
            final String click_time = "Click Time(GMT): " + conversionData.get("click_time") + "\n";
            final String is_first_launch = "Is First Launch: " + conversionData.get("is_first_launch") + "\n";
            InstallConversionData += install_type + media_source + install_time + click_time + is_first_launch;
            sessionCount++;
        }

    }

    public static Resources getAppResources() {
        return resources;
    }

}

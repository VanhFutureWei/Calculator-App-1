package com.hmsecosystem.calculator;

import android.content.Context;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;

import java.util.HashMap;
import java.util.Map;

public class AttributionAppsFlyer {

    private final Context context;

    private AttributionAppsFlyer(Context ctx){
       context = ctx;
    }

    public static AttributionAppsFlyer createAttributionAppsFlyer(Context ctx) {
        return new AttributionAppsFlyer(ctx);
    }

    public void TrackEvent(){

        /* Track Events in real time */
        Map<String, Object> eventValue = new HashMap<>();
        eventValue.put(AFInAppEventParameterName.REVENUE, 200);
        eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, "category_a");
        eventValue.put(AFInAppEventParameterName.CONTENT_ID, "1234567");
        eventValue.put(AFInAppEventParameterName.CURRENCY, "USD");
        //   AppsFlyerLib.getInstance().trackEvent(getApplicationContext(), AFInAppEventType.PURCHASE, eventValue);

        logInAppEvent();

    }
    public void logInAppEvent(){

        Add2Wishlist();
        logRevenue();
        logNegRevenue();
    }
    public void Add2Wishlist(){
        Map<String, Object> eventValues = new HashMap<>();
        eventValues.put(AFInAppEventParameterName.PRICE, 1234.56);
        eventValues.put(AFInAppEventParameterName.CONTENT_ID,"1234567");

        AppsFlyerLib.getInstance().logEvent(context,
                AFInAppEventType.ADD_TO_WISH_LIST , eventValues);
    }

    public void logRevenue(){
//        Map<String, Object> eventValues = new HashMap<String, Object>();
//        eventValues.put(AFInAppEventParameterName.CONTENT_ID, <ITEM_SKU>);
//        eventValues.put(AFInAppEventParameterName.CONTENT_TYPE, <ITEM_TYPE>);
//        eventValues.put(AFInAppEventParameterName.REVENUE, 200);
//
//        AppsFlyerLib.getInstance().logEvent(getApplicationContext(),
//                AFInAppEventType.PURCHASE, eventValues);
    }

    public void logNegRevenue(){
//        Map<String, Object> eventValues = new HashMap<String, Object>();
//        eventValues.put(AFInAppEventParameterName.REVENUE, -1234.56);
//        eventValues.put(AFInAppEventParameterName.CONTENT_ID,"1234567");
//        AppsFlyerLib.getInstance().logEvent(getApplicationContext(),
//                "cancel_purchase",
//                eventValues);
    }
}

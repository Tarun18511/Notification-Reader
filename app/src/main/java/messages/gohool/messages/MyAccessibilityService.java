package messages.gohool.messages;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAccessibilityService extends AccessibilityService {

//    private static final CharSequence[] NULL = {};
    public SharedPreferences pref;
    public static final String SHARED_PREFERENCES = "SharedPreferences";
    public static final String KEY = "Messages";

    public MyAccessibilityService() {
        Context ctx = this;
    }
    /**
     * Callback for {@link AccessibilityEvent}s.
     *
     * @param event The new event. This event is owned by the caller and cannot be used after
     *              this method returns. Services wishing to use the event after this method returns should
     *              make a copy.
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if(event.getPackageName().toString().equals("com.whatsapp")) {

            Parcelable parcelable = event.getParcelableData();
            Notification notif = (Notification) parcelable;
            pref = getSharedPreferences(SHARED_PREFERENCES, 0);
            SharedPreferences.Editor editor = pref.edit();
            Notification notification = (Notification) event.getParcelableData();
            CharSequence[] lines;
            lines = notification.extras.getCharSequenceArray(Notification.EXTRA_TEXT_LINES);


            int i = 0;
            String message = " ";
            String senderName = " ";
//
//            String previousData = " ";
////            if(lines == NULL) {
//                for(CharSequence msg : lines) {
////                senderName = " ";
//                    Log.d("Line " + i, (String) msg);
//                    message += (String) " " + msg;
//                    i += 1;
//                }
//
//            }
//            else {
//                senderName = extras.getString("android.title");
//                message = extras.getString("android.text");
//                previousData = pref.getString(KEY, "nothing is here");
//            }



//            senderName = extras.getString("android.title");
//            message = extras.getString("android.text");
            String previousData = pref.getString(KEY, "nothing is here");
            if(lines != null) { // To remove null pointer error giving any sequence of text
                for (CharSequence msg : lines) {
                    //                senderName = " ";
                    Log.d("Line " + i, (String) msg);
                    message += (String) "\n" + msg;
                    i += 1;
                }
            }

            int flag = 0;
//            if(flag != 0) {
//                previousData = pref.getString(KEY, "nothing is here");
//            }

            if(senderName != "WhatsApp")
            editor.putString(KEY, previousData + "\n" + senderName + ": " + message + "\n");
            editor.commit();
            flag++;
//            MainActivity mActivity = new MainActivity();
//            mActivity.pMesaage(extras);

//            String senderName = extras.getString("android.title");
//            String message = extras.getString("android.text");
//            SharedPreferences sharedPreferences = PreferenceManager
//                    .getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("Messages", senderName + ": " + message + "\n");
//            editor.apply();

//            Log.d("text: ", getSharedPreferences());
//            getSharedPreferences();
        }

    }

    public String getSharedPreferences() {
        pref = getSharedPreferences(SHARED_PREFERENCES, 0);
        return pref.getString(KEY, "nothing is here");
    }


    /**
     * Callback for interrupting the accessibility feedback.
     */
    @Override
    public void onInterrupt() {

    }
}

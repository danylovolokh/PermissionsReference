package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.net.Uri;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.ScrapHeap;

/**
 * Created by danylo.volokh on 4/13/16.
 */
public class Calendar_ContentProvider_uncanonicalize extends CalendarBaseDemonstrator {

    private static final String TAG = Calendar_ContentProvider_uncanonicalize.class.getSimpleName();

    public Calendar_ContentProvider_uncanonicalize(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() throws SecurityException {
        Log.v(TAG, ">> callDangerousMethod");
        boolean successful;

//        uncomment when you need it
//        logCalendarData(context());

        // This is very bad approach, please avoid accessing static fields like I do here
        Uri updateUri = ScrapHeap.sInsertedCalendarEventUri;
        if(updateUri == null) {
            callback().showToast("Please call insert first and repeat the action");
            successful = false;
        } else {
            context().getContentResolver().uncanonicalize(updateUri);
            successful = true;
        }

        Log.v(TAG, "callDangerousMethod, updateUri " + updateUri);
        Log.v(TAG, "<< callDangerousMethod");
        return successful;
    }
}

package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.ScrapHeap;

import java.util.Calendar;

/**
 * Created by danylo.volokh on 4/13/16.
 */
public class Calendar_ContentProvider_delete extends CalendarBaseDemonstrator {

    private static final String TAG = Calendar_ContentProvider_delete.class.getSimpleName();

    public Calendar_ContentProvider_delete(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() throws SecurityException {
        Log.v(TAG, ">> callDangerousMethod");
        boolean successful;

//        uncomment when you need it
//        logCalendarData(context());

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2016, Calendar.APRIL, 11, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2016, Calendar.APRIL, 11, 8, 45);

        ContentValues values = new ContentValues();
        // Update test event
        values.put(CalendarContract.Events.TITLE, "Updated Test Event");

        // This is very bad approach, please avoid accessing static fields like I do here
        Uri updateUri = ScrapHeap.sInsertedCalendarEventUri;
        if(updateUri == null) {
            callback().showToast("Please call insert first and then revoke permission to crash here");
            successful = false;
        } else {
            context().getContentResolver().delete(updateUri, null, null);
            successful = true;
        }

        Log.v(TAG, "callDangerousMethod, updateUri " + updateUri);
        Log.v(TAG, "<< callDangerousMethod");
        return successful;
    }
}

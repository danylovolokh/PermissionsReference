package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.ScrapHeap;

import java.util.Calendar;

/**
 * Created by danylo.volokh on 4/9/16.
 */
public class Calendar_ContentProvider_update extends CalendarBaseDemonstrator {
    private static final String TAG = Calendar_ContentProvider_update.class.getSimpleName();

    public Calendar_ContentProvider_update(String name, MethodDemonstrator.DemonstratorCallback demonstratorCallback) {
        super(name, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() {
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
            callback().showToast("Please call insert first and repeat the action");
            successful = false;
        } else {
            context().getContentResolver().update(updateUri, values, null, null);
            successful = true;
        }

        Log.v(TAG, "callDangerousMethod, updateUri " + updateUri);
        Log.v(TAG, "<< callDangerousMethod");
        return successful;
    }
}

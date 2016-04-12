package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

/**
 * Created by danylo.volokh on 4/8/16.
 *
 * This method {@link ContentResolver#query(Uri, String[], String, String[], String)}
 * is called when we need to get access to Calendar on the device
 */
public class Calendar_ContentProvider_query extends CalendarBaseDemonstrator {

    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.

    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    private static final String TAG = Calendar_ContentProvider_query.class.getSimpleName();

    public Calendar_ContentProvider_query(String name, DemonstratorCallback demonstratorCallback) {
        super(name, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() {
        Log.v(TAG, ">> callDangerousMethod");

        // Run query

        Cursor cur = null;
        ContentResolver cr = context().getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"sampleuser@gmail.com", "com.google",
                "sampleuser@gmail.com"};


        /**
         * Here we need a Permission
         */
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

        Log.v(TAG, "<< callDangerousMethod");

        return true;
    }
}

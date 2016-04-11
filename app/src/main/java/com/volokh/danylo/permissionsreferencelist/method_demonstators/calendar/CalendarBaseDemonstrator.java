package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;


/**
 * Created by danylo.volokh on 4/9/16.
 */
public abstract class CalendarBaseDemonstrator extends MethodDemonstrator {

    private static final String TAG = CalendarBaseDemonstrator.class.getSimpleName();

    public CalendarBaseDemonstrator(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName, demonstratorCallback);
    }

    void logCalendarData(Context c) {

        String projection[] = {"_id", "calendar_displayName"};
        Uri calendars;
        calendars = Uri.parse("content://com.android.calendar/calendars");

        ContentResolver contentResolver = c.getContentResolver();
        Cursor managedCursor = contentResolver.query(calendars, projection, null, null, null);

        if (managedCursor.moveToFirst()){

            String calName;
            String calID;
            int cont= 0;
            int nameCol = managedCursor.getColumnIndex(projection[1]);
            int idCol = managedCursor.getColumnIndex(projection[0]);
            do {
                calName = managedCursor.getString(nameCol);
                calID = managedCursor.getString(idCol);
                Log.v(TAG, "logCalendarData, calName[" + calName + "]");
                Log.v(TAG, "logCalendarData, calID[" + calID + "]");

                cont++;
            } while(managedCursor.moveToNext());
            managedCursor.close();
        }

//        String timezones[] = TimeZone.getAvailableIDs();
//        for (String timezone : timezones) {
//            Log.v(TAG, "logCalendarData, timezone[" + timezone + "]");
//        }
    }
}

package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.ScrapHeap;

import java.util.Calendar;

/**
 * Created by danylo.volokh on 4/9/16.
 *
 * Method insert allows us to insert items to Calendar on the device
 */
public class Calendar_ContentResolver_insert extends CalendarBaseDemonstrator {

    private static final String TAG = Calendar_ContentResolver_insert.class.getSimpleName();

    public Calendar_ContentResolver_insert(String name, MethodDemonstrator.DemonstratorCallback demonstratorCallback) {
        super(name, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() {
        Log.v(TAG, ">> callDangerousMethod");

//        logCalendarData(context());

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2016, Calendar.APRIL, 11, 7, 30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2016, Calendar.APRIL, 11, 8, 45);

        /**
         * This value is individual for every device
         *
         * All calendars was logged in method. See LogCat
         * {@link #logCalendarData(Context)}
         *
         */
        int calendarId = 4;

        ContentValues event = new ContentValues();

        event.put(CalendarContract.Events.CALENDAR_ID, calendarId);
        event.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
        event.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
        event.put(CalendarContract.Events.TITLE, "Test Event");
        event.put(CalendarContract.Events.DESCRIPTION, "This is test event");
        event.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");

        ScrapHeap.sInsertedCalendarEventUri = context().getContentResolver()
                .insert(CalendarContract.Events.CONTENT_URI, event);

        Log.v(TAG, "callDangerousMethod, sInsertedCalendarEventUri " + ScrapHeap.sInsertedCalendarEventUri);
        Log.v(TAG, "<< callDangerousMethod");

        return true;
    }
}

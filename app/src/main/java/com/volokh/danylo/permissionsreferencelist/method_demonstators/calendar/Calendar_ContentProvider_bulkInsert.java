package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.provider.CalendarContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.ScrapHeap;

import java.util.Calendar;

/**
 * Created by danylo.volokh on 4/12/16.
 */
public class Calendar_ContentProvider_bulkInsert extends CalendarBaseDemonstrator {

    private static final String TAG = Calendar_ContentProvider_bulkInsert.class.getSimpleName();

    public Calendar_ContentProvider_bulkInsert(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() throws SecurityException {

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
        event.put(CalendarContract.Events.TITLE, "Test Event bulk insert");
        event.put(CalendarContract.Events.DESCRIPTION, "This is test event");
        event.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");

        context().getContentResolver()
                .bulkInsert(CalendarContract.Events.CONTENT_URI, new ContentValues[]{event});

        Log.v(TAG, "callDangerousMethod, sInsertedCalendarEventUri " + ScrapHeap.sInsertedCalendarEventUri);
        Log.v(TAG, "<< callDangerousMethod");

        return true;
    }
}

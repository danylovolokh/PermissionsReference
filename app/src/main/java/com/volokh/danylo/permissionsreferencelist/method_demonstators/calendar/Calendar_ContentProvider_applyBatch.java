package com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar;

import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.CalendarContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.ScrapHeap;

import java.util.ArrayList;

/**
 * Created by danylo.volokh on 4/9/16.
 */
public class Calendar_ContentProvider_applyBatch extends CalendarBaseDemonstrator {

    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.

    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    private static final String TAG = Calendar_ContentProvider_query.class.getSimpleName();

    public Calendar_ContentProvider_applyBatch(String name, MethodDemonstrator.DemonstratorCallback demonstratorCallback) {
        super(name, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() {
        Log.v(TAG, ">> callDangerousMethod ");

        boolean successful;

        // This is very bad approach, please avoid accessing static fields like I do here
        Uri eventUri = ScrapHeap.sInsertedCalendarEventUri;
        if(eventUri == null) {
            callback().showToast("Please call insert first and repeat the action");
            successful = false;
        } else {
            ArrayList<ContentProviderOperation> ops =
                    new ArrayList<>();
            ops.add(
                    ContentProviderOperation.newUpdate(eventUri)
                            .withValue(CalendarContract.Events.TITLE, "Update with batch")
                            .withYieldAllowed(true)
                            .build());

            try {
                context().getContentResolver().applyBatch(CalendarContract.AUTHORITY, ops);
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (OperationApplicationException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            successful = true;

        }
        Log.v(TAG, "<< callDangerousMethod ");
        return successful;
    }
}

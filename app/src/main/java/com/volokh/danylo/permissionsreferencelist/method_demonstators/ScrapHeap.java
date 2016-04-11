package com.volokh.danylo.permissionsreferencelist.method_demonstators;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by danylo.volokh on 4/12/16.
 */
public class ScrapHeap {

    private static final String KEY_INSERTED_CALENDAR_EVENT_URI = "KEY_INSERTED_CALENDAR_EVENT_URI";

    /**
     *
     * I'm making this field public for simplicity.
     *
     * PLEASE. DO NOT DO THAT IN YOUR CODE.
     *
     * Thanks.
     *
     * Helper value for demonstration
     * Some methods of ContentProvider like {@link android.content.ContentProvider#update(Uri, ContentValues, String, String[])}
     * needs to have some input data to work with. This data can be provided by insert operation.
     * Result of the operation will be stored here
     */
    public static Uri sInsertedCalendarEventUri;

    public static void onSaveInstanceState(Bundle outState){
        outState.putParcelable(KEY_INSERTED_CALENDAR_EVENT_URI, sInsertedCalendarEventUri);
    }

    public static void onRestoreInstanceState(Bundle savedState){
        sInsertedCalendarEventUri = savedState.getParcelable(KEY_INSERTED_CALENDAR_EVENT_URI);
    }
}

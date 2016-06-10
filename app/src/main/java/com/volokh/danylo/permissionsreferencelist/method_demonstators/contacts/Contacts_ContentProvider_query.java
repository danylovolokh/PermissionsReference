package com.volokh.danylo.permissionsreferencelist.method_demonstators.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;

/**
 * Created by danylo.volokh on 4/8/16.
 *
 * This method {@link ContentResolver#query(Uri, String[], String, String[], String)}
 * is called when we need to get access to Contacts on the device
 */
public class Contacts_ContentProvider_query extends MethodDemonstrator {

    // Sets the columns to retrieve for the user profile
    public static final String[] PROJECTION = new String[]
    {
        ContactsContract.Profile._ID,
                ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
                ContactsContract.Profile.LOOKUP_KEY,
                ContactsContract.Profile.PHOTO_THUMBNAIL_URI
    };

    private static final String TAG = Contacts_ContentProvider_query.class.getSimpleName();

    public Contacts_ContentProvider_query(String name, MethodDemonstrator.DemonstratorCallback demonstratorCallback) {
        super(name, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() {
        Log.v(TAG, ">> callDangerousMethod");

        // Retrieves the profile from the Contacts Provider

        Cursor cursor = context().getContentResolver().query(
                ContactsContract.Profile.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null);

        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Profile.DISPLAY_NAME_PRIMARY));
        Log.v(TAG, "callDangerousMethod name " + name);
        Log.v(TAG, "<< callDangerousMethod");

        return true;
    }
}

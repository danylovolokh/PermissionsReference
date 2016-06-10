package com.volokh.danylo.permissionsreferencelist.method_demonstators.contacts;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;

/**
 * Created by danylo.volokh on 4/16/16.
 */
public class ContactsLoader_initLoader extends MethodDemonstrator{

    private static final String[] PROJECTION = {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE,
            ContactsContract.Contacts.DISPLAY_NAME_SOURCE,
            ContactsContract.Contacts.PHOTO_URI,
            ContactsContract.CommonDataKinds.Email.ADDRESS
    };

    private static final String SELECTION =
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ? " + "OR " +
                    ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE + " LIKE ? " + "OR " +
                    ContactsContract.Contacts.DISPLAY_NAME_SOURCE + " LIKE ? " + "OR " +
                    ContactsContract.CommonDataKinds.Email.ADDRESS + " LIKE ? " + "AND " +
                    ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE + "'";

    private static final String TAG = ContactsLoader_initLoader.class.getSimpleName();

    public ContactsLoader_initLoader(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() throws SecurityException {

        callback().activity().getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return new CursorLoader(
                        callback().activity(),
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        PROJECTION,
                        SELECTION, new String[]{"%", "%", "%", "%"}, null
                );

            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                Log.v(TAG, "onLoadFinished data, " + data);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        });
        return false;
    }
}

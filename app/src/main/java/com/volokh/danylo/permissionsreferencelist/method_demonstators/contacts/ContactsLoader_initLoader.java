package com.volokh.danylo.permissionsreferencelist.method_demonstators.contacts;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by danylo.volokh on 4/16/16.
 */
public class ContactsLoader_initLoader extends MethodDemonstrator{

    private final AtomicBoolean mSync = new AtomicBoolean();

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
        Log.v(TAG, "callDangerousMethod");

        // this is just a wrapper
        final AtomicReference<SecurityException> securityException = new AtomicReference<>();

        synchronized (mSync){

            callback().activity().getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    Log.v(TAG, "onCreateLoader");

                    return new CursorLoader(
                            callback().activity(),
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            PROJECTION,
                            SELECTION, new String[]{"%", "%", "%", "%"}, null
                    ) {
                        @Override
                        protected Cursor onLoadInBackground() {
                            Log.v(TAG, "onLoadInBackground");

                            Cursor cursor = null;
                            try {

                                cursor = super.onLoadInBackground();

                            } catch (final SecurityException e) {
                                securityException.set(e);
                            }

                            synchronized (mSync){
                                mSync.notify();
                                // called notify
                                mSync.set(true);
                            }

                            return cursor;
                        }
                    };

                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    Log.v(TAG, "onLoadFinished data, " + data);

                    synchronized (mSync){
                        mSync.notify();
                        // called notify
                        mSync.set(true);
                    }

                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {

                }
            });

            try {
                if(!mSync.get()){
                    // if notify not called then wait. Sometimes loader returns onLoadFinished synchronously
                    mSync.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // if we get exception - throw it
            if(securityException.get() != null){
                throw securityException.get();
            }

        }

        return true;
    }
}

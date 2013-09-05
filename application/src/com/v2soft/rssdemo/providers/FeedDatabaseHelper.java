package com.v2soft.rssdemo.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.v2soft.rssdemo.domains.FeedItem;

/**
 * Local data base open helper.
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 *
 */
public class FeedDatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = FeedDatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "rssfeed.sqlite";
    private static final int DATABASE_VERSION = 4;
    // Database table
    public static final String TABLE_FEED_ITEMS = "feeditems";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " 
            + TABLE_FEED_ITEMS
            + "("+FeedItem.FIELD_ID+" integer primary key autoincrement, " 
            + FeedItem.FIELD_AUTHOR + " text not null, " 
            + FeedItem.FIELD_DESCRIPTION + " text not null, " 
            + FeedItem.FIELD_FULLTEXT + " text, " 
            + FeedItem.FIELD_IMAGE + " text, " 
            + FeedItem.FIELD_LINK + " text, " 
            + FeedItem.FIELD_TITLE + " text not null, " 
            + FeedItem.FIELD_PUBLISH_DATE + " integer not null, "
            + "UNIQUE ("+FeedItem.FIELD_PUBLISH_DATE+", "+FeedItem.FIELD_TITLE+")"
            + ");";

    public FeedDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEED_ITEMS);
        onCreate(db);
    }

}

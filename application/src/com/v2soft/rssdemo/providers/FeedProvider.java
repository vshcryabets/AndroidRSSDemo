package com.v2soft.rssdemo.providers;

import com.v2soft.rssdemo.domains.FeedItem;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Feed content provider.
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 *
 */
public class FeedProvider extends ContentProvider {
    public static final String PROVIDER_NAME = "com.v2soft.rssdemo.providers.FeedProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://"+ PROVIDER_NAME + "/items");
    private UriMatcher mUriMatcher;
    private static final int FEED_ITEMS = 1;
    private static final int ITEM_ID = 2;
    private FeedDatabaseHelper mDatabase;

    @Override
    public boolean onCreate() {
        mDatabase = new FeedDatabaseHelper(getContext());
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(PROVIDER_NAME, "items", FEED_ITEMS);
        mUriMatcher.addURI(PROVIDER_NAME, "items/#", ITEM_ID);
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (mUriMatcher.match(uri)){
        case FEED_ITEMS:
            return "vnd.android.cursor.dir/com.v2soft.rssdemo.providers.items";
        case ITEM_ID: 
            return "vnd.android.cursor.item/com.v2soft.rssdemo.providers.items";
        default:
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = mUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mDatabase.getWritableDatabase();
        long id = 0;
        values.remove(FeedItem.FIELD_ID);
        switch (uriType) {
        case FEED_ITEMS:
            id = sqlDB.insert(FeedDatabaseHelper.TABLE_FEED_ITEMS, null, values);
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse("items/" + id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        // prepare query
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(FeedDatabaseHelper.TABLE_FEED_ITEMS);

        int uriType = mUriMatcher.match(uri);
        switch (uriType) {
        case FEED_ITEMS:
            break;
        case ITEM_ID:
            queryBuilder.appendWhere(FeedItem.FIELD_ID + "=" + uri.getLastPathSegment());
            break;
        default:
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        SQLiteDatabase db = mDatabase.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        return 0;
    }
}

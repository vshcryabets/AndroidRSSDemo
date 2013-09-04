package com.v2soft.rssdemo.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.IntentService;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.v2soft.rssdemo.domains.Feed;
import com.v2soft.rssdemo.domains.FeedItem;

/**
 * Сервис для получения ленты новостей и обновления локального харнилища. 
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 *
 */
public class RefreshFeedService extends IntentService {
    private static final String LOG_TAG = RefreshFeedService.class.getSimpleName();
    private static final String TAG_ITEM = "item";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_PUBDATE = "pubDate";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_LINK = "link";
    private static final String TAG_AUTHOR = "author";
    private static final SimpleDateFormat PUB_DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.US);

    public RefreshFeedService() {
        super(LOG_TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            ConnectivityManager mConnectivity;
            //            mConnectivity.
            final Feed feed = retreiveFeed();
            updateLocalStorage(feed);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.toString(), e);
        }
    }

    private void updateLocalStorage(Feed feed) {
        // TODO Auto-generated method stub
    }

    private Feed retreiveFeed() throws IOException, XmlPullParserException {
        final Feed result = new Feed();
        // open URLConnection
        URL url = new URL("http://habrahabr.ru/rss/hubs/"); 
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(connection.getInputStream(), "UTF-8");
        // parse loop
        int eventType = parser.getEventType();
        String tagName = null;
        String value = null;
        boolean processingHeaders = true;
        FeedItem item = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if ( eventType == XmlPullParser.START_TAG ) {
                tagName = parser.getName();
                value = null;
                if ( tagName.equals(TAG_ITEM)) {
                    item = new FeedItem();
                    result.getItems().add(item);
                    processingHeaders = false;
                }
            } else if (eventType == XmlPullParser.TEXT) {
                value = parser.getText();
            } else if (eventType == XmlPullParser.END_TAG ) {
                if ( processingHeaders ) {
                    processRSSHeaders(tagName, value, result);
                } else {
                    processRSSItem(tagName, value, item);
                }
            }
            eventType = parser.next();
        }
        return result;
    }

    private void processRSSItem(String tagName, String value, FeedItem item) {
        if ( tagName.equals(TAG_TITLE)) {
            item.setTitle(value);
        } else if ( tagName.equals(TAG_DESCRIPTION)) {
            item.setDescription(value);
        } else if ( tagName.equals(TAG_LINK)) {
            item.setLink(value);
        } else if ( tagName.equals(TAG_AUTHOR)) {
            item.setAuthor(value);
        } else if ( tagName.equals(TAG_CATEGORY)) {
            item.getCategories().add(value);
        } else if ( tagName.equals(TAG_PUBDATE)) {
            try {
                item.setPubDate(PUB_DATE_FORMATTER.parse(value));
            } catch (ParseException e) {
                // nothing to do...
            }
        }
    }

    private void processRSSHeaders(String tagName, String value, Feed result) {
        if ( tagName.equals(TAG_TITLE)) {
            result.setTitle(value);
        } else if ( tagName.equals(TAG_DESCRIPTION)) {
            result.setDescription(value);
        } else if ( tagName.equals(TAG_LINK)) {
            result.setLink(value);
        }
    }
}

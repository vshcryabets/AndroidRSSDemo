package com.v2soft.rssdemo.fragments;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

import com.v2soft.rssdemo.R;
import com.v2soft.rssdemo.domains.FeedItem;
import com.v2soft.rssdemo.providers.FeedProvider;
import com.v2soft.rssdemo.services.RefreshFeedService;

/**
 * RSS items list fragment.
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class FeedListFragment extends Fragment implements OnClickListener, LoaderManager.LoaderCallbacks<Cursor>, OnItemClickListener{
    private final static String[] DISPLAY_COLUMNS = new String[] { FeedItem.FIELD_ID, FeedItem.FIELD_PUBLISH_DATE, 
            FeedItem.FIELD_TITLE, FeedItem.FIELD_AUTHOR,
            FeedItem.FIELD_DESCRIPTION, FeedItem.FIELD_LINK};
    private static final String LOG_TAG = FeedListFragment.class.getSimpleName();
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault());
    
    protected static final int COLUMN_DATE = 1;
    protected static final int COLUMN_DESCRIPTION = 4;
    private static final int COLUMN_LINK = 5;

    private SimpleCursorAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_feed_list, null);
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        view.findViewById(R.id.btn_refresh).setOnClickListener(this);
        // Fields on the UI to which we map
        int[] to = new int[] { 0, R.id.txt_date, R.id.txt_title, R.id.txt_author, R.id.txt_description, 0 };

        getLoaderManager().initLoader(0, null, this);
        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.listitem_feed, null, DISPLAY_COLUMNS, to);
        mAdapter.setViewBinder(mViewBinder);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        getActivity().startService(new Intent(getActivity(), RefreshFeedService.class));
    }

    public static Fragment newInstance() {
        return new FeedListFragment();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        CursorLoader cursorLoader = new CursorLoader(getActivity(), 
                FeedProvider.CONTENT_URI, DISPLAY_COLUMNS, null, null, FeedItem.FIELD_PUBLISH_DATE+" DESC");
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
        mAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAdapter.changeCursor(null);
    }
    
    private ViewBinder mViewBinder = new ViewBinder() {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            if ( columnIndex == COLUMN_DATE ) {
                // date
                Date date = new Date(cursor.getLong(columnIndex));
                ((TextView)view).setText(SDF.format(date));
                return true;
            } else if ( columnIndex == COLUMN_DESCRIPTION ) {
                final TextView text =  (TextView)view;
                text.setText(Html.fromHtml(cursor.getString(columnIndex))); //, new URLImageParser(text, getActivity()), null)); disable, URLImageParser unstable
                return true;
            }
            return false;
        }
    };

    @Override
    public void onItemClick(AdapterView<?> arg0, View listView, int position, long id) {
        CursorWrapper wrapper = (CursorWrapper) mAdapter.getItem(position);
        String link = wrapper.getString(COLUMN_LINK);
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, FeedWebFragment.newInstance(link));
        transaction.addToBackStack(LOG_TAG);
        transaction.commit();
    }
}

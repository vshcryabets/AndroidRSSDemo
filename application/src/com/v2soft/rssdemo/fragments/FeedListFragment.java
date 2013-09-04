package com.v2soft.rssdemo.fragments;

import com.v2soft.rssdemo.R;
import com.v2soft.rssdemo.services.RefreshFeedService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * RSS items list fragment.
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class FeedListFragment extends Fragment implements OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_feed_list, null);
		ListView listView = (ListView) view.findViewById(android.R.id.list);
		view.findViewById(R.id.btn_refresh).setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
        getActivity().startService(new Intent(getActivity(), RefreshFeedService.class));
	}

	public static Fragment newInstance() {
		return new FeedListFragment();
	}
}

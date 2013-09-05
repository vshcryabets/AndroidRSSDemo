package com.v2soft.rssdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.v2soft.rssdemo.R;

/**
 * Web view fragment.
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class FeedWebFragment extends Fragment {
    private static final String EXTRA_LINK = "link";
    private WebView mWebView;
    private ProgressBar mProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_web_view, null);
        mWebView = (WebView) view.findViewById(R.id.webView);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);
        mWebView.setWebViewClient(mClient);
        mWebView.loadUrl(getArguments().getString(EXTRA_LINK));
        return view;
    }

    public static Fragment newInstance(String link) {
        Fragment result = new FeedWebFragment();
        final Bundle args = new Bundle();
        args.putString(EXTRA_LINK, link);
        result.setArguments(args);
        return result;
    }

    private WebViewClient mClient = new WebViewClient(){
        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
            mProgress.setVisibility(View.VISIBLE);
        };
        public void onPageFinished(WebView view, String url) {
            mProgress.setVisibility(View.INVISIBLE);
        };
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mWebView.loadUrl(url);
            return true;
        }
    };
}

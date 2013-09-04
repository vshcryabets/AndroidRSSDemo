package com.v2soft.rssdemo.domains;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * RSS feed container class.
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 *
 */
public class Feed implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mTitle;
    private String mDescription;
    private String mLink;
    private List<FeedItem> mItems;

    public Feed() {
        mItems = new LinkedList<FeedItem>();
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    public String getLink() {
        return mLink;
    }
    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public List<FeedItem> getItems() {
        return mItems;
    }

    public void setItems(List<FeedItem> mItems) {
        this.mItems = mItems;
    }
}

package com.v2soft.rssdemo.domains;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * RSS item container class.
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 *
 */
public class FeedItem {
    private String mTitle;
    private String mDescription;
    private String mFulltext;
    private String mImage;
    private Date mPubDate;
    private List<String> mCategories;
    private String mLink;
    private String mAuthor;
    private long mId;

    public FeedItem() {
        setCategories(new LinkedList<String>());
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
    public String getFulltext() {
        return mFulltext;
    }
    public void setFulltext(String mFulltext) {
        this.mFulltext = mFulltext;
    }
    public String getImage() {
        return mImage;
    }
    public void setImage(String mImage) {
        this.mImage = mImage;
    }
    public Date getPubDate() {
        return mPubDate;
    }
    public void setPubDate(Date mPubDate) {
        this.mPubDate = mPubDate;
    }
    public String getLink() {
        return mLink;
    }
    public void setLink(String mLink) {
        this.mLink = mLink;
    }
    public long getId() {
        return mId;
    }
    public void setId(long mId) {
        this.mId = mId;
    }
    public List<String> getCategories() {
        return mCategories;
    }
    public void setCategories(List<String> mCategories) {
        this.mCategories = mCategories;
    }
    public String getAuthor() {
        return mAuthor;
    }
    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}

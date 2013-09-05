package com.v2soft.rssdemo.domains;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.v2soft.androidcursor.CursorDataAnnotation;

/**
 * RSS item container class.
 * @author Vladimir Shcryabets <vshcryabets@gmail.com>
 *
 */
public class FeedItem {
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_FULLTEXT = "fulltext";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_PUBLISH_DATE = "publishDate";
    public static final String FIELD_LINK = "link";
    public static final String FIELD_AUTHOR = "author";
    public static final String FIELD_ID = "_id";
    
    @CursorDataAnnotation(columnName=FIELD_TITLE)
    private String mTitle;
    @CursorDataAnnotation(columnName=FIELD_DESCRIPTION)
    private String mDescription;
    @CursorDataAnnotation(columnName=FIELD_FULLTEXT)
    private String mFulltext;
    @CursorDataAnnotation(columnName=FIELD_IMAGE)
    private String mImage;
    @CursorDataAnnotation(columnName=FIELD_PUBLISH_DATE)
    private Date mPubDate;
    private List<String> mCategories;
    @CursorDataAnnotation(columnName=FIELD_LINK)
    private String mLink;
    @CursorDataAnnotation(columnName=FIELD_AUTHOR)
    private String mAuthor;
    @CursorDataAnnotation(columnName=FIELD_ID)
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

package me.joogle;

import java.net.URL;

/**
 * An individual search result
 */
public class Result {
    /**
     * The full URL of the result
     */
    public URL fullUrl;
    /**
     * Shorten URL of the search result better for displaying
     */
    public String shortUrl;
    /**
     * The title of the web page the result refers to
     */
    public String title;
    /**
     * The content which is displayed under the result on the Google result list
     */
    public String content;
    /**
     * The original JSOn from Google containing more information about the result
     */
    public String json;
}

package uk.dreamr.rhdev.dreamrsupportkit;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportEndPoint {
    private final String url;
    private static String URL_test;

    public SupportEndPoint(final String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

}

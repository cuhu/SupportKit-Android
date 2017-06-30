package uk.dreamr.rhdev.dreamrsupportkit;

/**
 * Created by mylokaye on 28/06/2017.
 */

public class SupportKitBuilderException extends RuntimeException{

    private String message;
    private Throwable cause;

    public SupportKitBuilderException(){
        super();
    }

    public SupportKitBuilderException(String message, Throwable cause){
        super(message, cause);
        this.cause = cause;
        this.message = message;
    }
}

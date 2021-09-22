package dreamkite.common.Exception;

import java.util.Locale;

public class DreamKiteException extends Exception{
    public DreamKiteException(String message){
        super(message);
        System.out.println(String.format(Locale.ENGLISH,"DreamKite %s",
                message));
    }
}


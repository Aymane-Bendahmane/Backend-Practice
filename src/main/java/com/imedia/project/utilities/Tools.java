package com.imedia.project.utilities;

public class Tools {

    public static final String ERROR_MESSAGE = "Technical Error";
    public static final String BAD_PARAMETER = "Bad parameters" ;
    public static final String ERROR_EXTERNAL_API_CALL = "Error when calling external api" ;
    public static final String ERROR_CASTING_OBJECTS= "Error when casting objects" ;
    public static final String FROM= "from" ;
    public static final String TO= "to" ;
    public static final String AMOUNT= "amount" ;
    public static final String INTERNAL_SERVER_ERROR= "Internal server error" ;

    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.isEmpty());
    }

}

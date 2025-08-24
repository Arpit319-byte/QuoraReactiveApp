package com.example.QuoraReactiveApp.Utils;

import java.time.LocalDateTime;

public class CursorUtil {

    public static boolean isValidCursor(String cursor){

        if(cursor == null || cursor.isEmpty())
            return false;

        try{
            LocalDateTime.parse(cursor);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static LocalDateTime parseCursor(String cursor){

        if(!isValidCursor(cursor)) {
            throw new IllegalArgumentException("Invalid Cursor");
        }

        return LocalDateTime.parse(cursor);
    }
}

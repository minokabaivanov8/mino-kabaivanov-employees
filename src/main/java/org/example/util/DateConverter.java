package org.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static Date  convertStringToDate(String dateString) {
        String[] formats = {"yyyy-MM-dd", "dd-MM-yy", "dd-MM-yyyy"};

        for (String format : formats) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                simpleDateFormat.setLenient(false); // Disable lenient parsing
                return simpleDateFormat.parse(dateString);
            } catch (ParseException _) {
            }
        }

        // If none of the formats match, return null
        return null;
    }

}

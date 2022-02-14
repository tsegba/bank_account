package bank.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class DateUtils {
    private String datePattern;

    public DateUtils() {
        super();
    }

    public DateUtils(String datePattern) {
        this.datePattern = datePattern;
    }

    public Date getCurrenteDate(){
        return new Date();
    }

   public String format(Date date){
       return new SimpleDateFormat(datePattern).format(date);
    }
    public Date format(String date){
        try {
            return new SimpleDateFormat(datePattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

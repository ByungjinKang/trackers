package org.tracker.common.convert;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateToDate {
    public static Date convert(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
}

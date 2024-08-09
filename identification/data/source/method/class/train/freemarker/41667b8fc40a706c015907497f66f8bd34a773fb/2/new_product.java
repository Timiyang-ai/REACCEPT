public static String dateToISO8601String(
            Date date,
            boolean datePart, boolean timePart, boolean offsetPart,
            int accuracy,
            TimeZone timeZone,
            DateToISO8601CalendarFactory calendarFactory) {
        return dateToString(date, datePart, timePart, offsetPart, accuracy, timeZone, false, calendarFactory);
    }
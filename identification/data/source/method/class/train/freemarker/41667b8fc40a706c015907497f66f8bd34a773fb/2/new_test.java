    private String dateToISO8601String(
            Date date,
            boolean datePart, boolean timePart, boolean offsetPart,
            int accuracy,
            TimeZone timeZone) {
        return DateUtil.dateToISO8601String(
                date,
                datePart, timePart, offsetPart,
                accuracy,
                timeZone,
                calendarFactory);        
    }
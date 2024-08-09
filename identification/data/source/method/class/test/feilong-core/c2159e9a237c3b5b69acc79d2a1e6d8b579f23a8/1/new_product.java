public static int getHourOfYear(Date date){
        Validate.notNull(date, "date can't be null!");
        return (getDayOfYear(date) - 1) * 24 + CalendarUtil.getFieldValue(date, Calendar.HOUR_OF_DAY);
    }
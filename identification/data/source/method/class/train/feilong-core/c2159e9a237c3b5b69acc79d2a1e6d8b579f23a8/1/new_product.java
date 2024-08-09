public static int getDayOfYear(Date date){
        Validate.notNull(date, "date can't be null!");
        return CalendarUtil.getFieldValue(date, Calendar.DAY_OF_YEAR);
    }
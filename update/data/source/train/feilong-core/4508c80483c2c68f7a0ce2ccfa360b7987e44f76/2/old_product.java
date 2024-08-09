public static final int getMonth(Date date){
        return CalendarUtil.getCalendarFieldValue(date, Calendar.MONTH) + 1;
    }
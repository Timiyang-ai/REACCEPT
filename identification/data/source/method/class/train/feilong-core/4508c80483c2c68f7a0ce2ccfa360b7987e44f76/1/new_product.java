public static final int getMonth(Date date){
        return 1 + CalendarUtil.getCalendarFieldValue(date, Calendar.MONTH);
    }
public static Date getLastDateOfThisDay(Date date){
        Validate.notNull(date, "date can't be null!");
        Calendar calendar = toCalendar(date);
        return CalendarUtil.toDate(CalendarUtil.resetDayEnd(calendar));
    }
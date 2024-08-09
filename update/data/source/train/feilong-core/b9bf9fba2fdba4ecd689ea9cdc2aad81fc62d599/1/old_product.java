@Deprecated
    public static Date[] getExtentYesterday(){
        Calendar calendar = CalendarUtil.resetCalendarByDay(new Date());
        Date today = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        return new Date[] { yesterday, today };
    }
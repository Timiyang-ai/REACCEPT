public static Date[] getResetYesterdayAndToday(){
        Calendar calendar = CalendarUtil.resetCalendarByDay(new Date());
        Date today = calendar.getTime();
        return new Date[] { DateUtil.getYesterday(today), today };
    }
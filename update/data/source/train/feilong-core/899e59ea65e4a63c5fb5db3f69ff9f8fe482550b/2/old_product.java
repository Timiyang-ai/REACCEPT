public static Date[] getResetTodayAndTomorrow(){
        Calendar calendar = CalendarUtil.resetDayBegin(new Date());
        Date today = calendar.getTime();
        return new Date[] { today, DateUtil.addDay(today, 1) };
    }
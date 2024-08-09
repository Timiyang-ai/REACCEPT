public static Date[] getResetYesterdayAndToday(){
        Calendar calendar = CalendarUtil.resetDayBegin(new Date());
        Date today = calendar.getTime();
        return new Date[] { DateUtil.addDay(today, -1), today };
    }
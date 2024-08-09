public static Date[] getResetTodayAndTomorrow(){
        Calendar calendar = CalendarUtil.resetCalendarByDay(new Date());
        Date today = calendar.getTime();
        // ***************************
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();
        return new Date[] { today, tomorrow };
    }
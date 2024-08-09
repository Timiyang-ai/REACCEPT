public static Date[] getResetTodayAndTomorrow(){
        Date today = DateUtil.getFirstDateOfThisDay(new Date());
        return new Date[] { today, DateUtil.addDay(today, 1) };
    }
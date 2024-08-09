public static Date[] getResetYesterdayAndToday(){
        Date today = DateUtil.getFirstDateOfThisDay(new Date());
        return new Date[] { DateUtil.addDay(today, -1), today };
    }
public static List<Date> getIntervalDayList(String fromDateString,String toDateString,String datePattern){

        Date fromDate = DateUtil.string2Date(fromDateString, datePattern);
        Date toDate = DateUtil.string2Date(toDateString, datePattern);

        Date minDate = fromDate.before(toDate) ? fromDate : toDate;
        Date maxDate = fromDate.before(toDate) ? toDate : fromDate;

        // ******重置时间********
        Date beginDateReset = DateUtil.getFirstDateOfThisDay(minDate);
        Date endDateReset = DateUtil.getLastDateOfThisDay(maxDate);

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(beginDateReset);

        // 相隔的天数
        int intervalDay = DateUtil.getIntervalDay(beginDateReset, endDateReset);
        for (int i = 0; i < intervalDay; ++i){
            dateList.add(DateUtil.addDay(beginDateReset, i + 1));
        }

        return dateList;
    }
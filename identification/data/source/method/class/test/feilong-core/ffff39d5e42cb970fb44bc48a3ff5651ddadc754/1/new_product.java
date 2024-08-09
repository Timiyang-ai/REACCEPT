public static List<Date> getIntervalDayList(String fromDateString,String toDateString,String datePattern){
        Validate.notBlank(fromDateString, "fromDateString can't be null/empty!");
        Validate.notBlank(toDateString, "toDateString can't be null/empty!");
        Validate.notBlank(datePattern, "datePattern can't be null/empty!");

        Date fromDate = DateUtil.toDate(fromDateString, datePattern);
        Date toDate = DateUtil.toDate(toDateString, datePattern);

        return getIntervalDayList(fromDate, toDate);
    }
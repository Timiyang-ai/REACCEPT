public static List<Date> getIntervalDayList(String fromDateString,String toDateString,String datePattern){
        Validate.notBlank(fromDateString, "fromDateString can't be null/empty!");
        Validate.notBlank(toDateString, "toDateString can't be null/empty!");
        Validate.notBlank(datePattern, "datePattern can't be null/empty!");

        Date fromDate = DateUtil.string2Date(fromDateString, datePattern);
        Date toDate = DateUtil.string2Date(toDateString, datePattern);

        return getIntervalDayList(fromDate, toDate);
    }
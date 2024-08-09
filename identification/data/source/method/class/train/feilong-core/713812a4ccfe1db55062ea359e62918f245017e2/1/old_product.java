public static List<Date> getIntervalDayList(String fromDateString,String toDateString,String datePattern){
        List<Date> dateList = new ArrayList<Date>();
        //***************************************************************/
        Date beginDate = DateUtil.string2Date(fromDateString, datePattern);
        Date endDate = DateUtil.string2Date(toDateString, datePattern);
        // ******重置时间********
        Date beginDateReset = DateUtil.getFirstDateOfThisDay(beginDate);
        Date endDateReset = DateUtil.getLastDateOfThisDay(endDate);
        //***************************************************************/
        // 相隔的天数
        int intervalDay = DateUtil.getIntervalDay(beginDateReset, endDateReset);
        //***************************************************************/
        Date minDate = beginDateReset;
        if (beginDateReset.equals(endDateReset)){
            minDate = beginDateReset;
        }else if (beginDateReset.before(endDateReset)){
            minDate = beginDateReset;
        }else{
            minDate = endDateReset;
        }
        //***************************************************************/
        dateList.add(minDate);
        //***************************************************************/
        if (intervalDay > 0){
            for (int i = 0; i < intervalDay; ++i){
                dateList.add(DateUtil.addDay(minDate, i + 1));
            }
        }
        return dateList;
    }
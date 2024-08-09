@Deprecated
    public static String toHumanizationDateString(Date inDate){
        Date nowDate = new Date();

        // 传过来的日期的年份
        int inYear = DateUtil.getYear(inDate);
        //**************************************************************************************/
        // 当前时间的年
        int currentYear = DateUtil.getYear(nowDate);
        //是否是同一年
        boolean isSameYear = currentYear == inYear;
        //**************************************************************************************/
        // 任意日期和现在相差的毫秒数
        long spaceTime = DateUtil.getIntervalTime(inDate, nowDate);
        // 相差天数
        int spaceDay = DateUtil.getIntervalDay(spaceTime);
        //**************************************************************************************/

        switch (spaceDay) {
            case 0: // 间隔0天
                int spaceHour = DateUtil.getIntervalHour(spaceTime); // 相差小时数
                if (spaceHour == 0){// 小时间隔
                    int spaceMinute = DateUtil.getIntervalMinute(spaceTime);
                    if (spaceMinute == 0){
                        int spaceSecond = DateUtil.getIntervalSecond(spaceTime);
                        return spaceSecond + SECOND + "前";
                    }
                    return spaceMinute + MINUTE + "前";
                }
                // 传过来的日期的日
                int inDay = DateUtil.getDayOfMonth(inDate);
                // 当前时间的日
                int currentDayOfMonth = DateUtil.getDayOfMonth(nowDate);
                if (inDay == currentDayOfMonth){
                    return spaceHour + HOUR + "前";
                }
                return YESTERDAY + " " + DateUtil.date2String(inDate, DatePattern.COMMON_TIME_WITHOUT_SECOND);
            case 1: // 间隔一天
                if (DateUtil.isEquals(DateUtil.addDay(inDate, 1), nowDate, DatePattern.COMMON_DATE)){
                    return YESTERDAY + " " + DateUtil.date2String(inDate, DatePattern.COMMON_TIME_WITHOUT_SECOND);
                }
                return THEDAY_BEFORE_YESTERDAY + " " + DateUtil.date2String(inDate, DatePattern.COMMON_TIME_WITHOUT_SECOND);
            case 2: // 间隔2天
                if (DateUtil.isEquals(DateUtil.addDay(inDate, 2), nowDate, DatePattern.COMMON_DATE)){
                    return THEDAY_BEFORE_YESTERDAY + " " + DateUtil.date2String(inDate, DatePattern.COMMON_TIME_WITHOUT_SECOND);
                }
                if (isSameYear){
                    return DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_YEAR_AND_SECOND);
                }
                return DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_SECOND);
            default://spaceDay > 2     // 间隔大于2天
                if (isSameYear){
                    return DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_YEAR_AND_SECOND);
                }
                return DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_SECOND);
        }
    }
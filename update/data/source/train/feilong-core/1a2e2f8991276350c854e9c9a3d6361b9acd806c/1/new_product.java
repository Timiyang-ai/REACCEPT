public static String toPrettyDateString(Date inDate){
        Date nowDate = new Date();

        // 传过来的日期的年份
        int inYear = DateUtil.getYear(inDate);
        //**************************************************************************************/
        int currentYear = DateUtil.getYear(nowDate);// 当前时间的年
        boolean isSameYear = currentYear == inYear;//是否是同一年
        long spaceTime = DateUtil.getIntervalTime(inDate, nowDate);// 任意日期和现在相差的毫秒数
        int spaceDay = DateUtil.getIntervalDay(spaceTime);// 相差天数
        //**************************************************************************************/
        switch (spaceDay) {
            case 0: // 间隔0天
                return doWithZeroDayInterval(inDate, nowDate, spaceTime);
            case 1: // 间隔一天
                return doWithOneDayInterval(inDate, nowDate);
            case 2: // 间隔2天
                return doWithTwoDaysInterval(inDate, nowDate, isSameYear);
            default://spaceDay > 2     // 间隔大于2天
                if (isSameYear){
                    return DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_YEAR_AND_SECOND);
                }
                return DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_SECOND);
        }
    }
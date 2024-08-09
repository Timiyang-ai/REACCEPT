@Deprecated
    public static String toHumanizationDateString(Date inDate){
        Date nowDate = new Date();

        //**************************************************************************************/
        String returnValue = null;
        // 传过来的日期的年份
        int inYear = DateUtil.getYear(inDate);
        // 传过来的日期的月份
        // int inMonth = getMonth(inDate);
        // 传过来的日期的日
        int inDay = DateUtil.getDayOfMonth(inDate);

        //**************************************************************************************/
        // 当前时间的年
        int year = DateUtil.getYear(nowDate);
        // 当前时间的余额
        // int month = getMonth();
        // 当前时间的日
        int day = DateUtil.getDayOfMonth(nowDate);

        //**************************************************************************************/
        // 任意日期和现在相差的毫秒数
        long spaceTime = DateUtil.getIntervalTime(inDate, nowDate);
        // 相差天数
        int spaceDay = DateUtil.getIntervalDay(spaceTime);
        // 相差小时数
        int spaceHour = DateUtil.getIntervalHour(spaceTime);
        // 相差分数
        int spaceMinute = DateUtil.getIntervalMinute(spaceTime);
        // 相差秒数
        int spaceSecond = DateUtil.getIntervalSecond(spaceTime);
        //**************************************************************************************/
        // 间隔一天
        if (spaceDay == 1){
            if (DateUtil.isEquals(DateUtil.addDay(inDate, 1), nowDate, DatePattern.COMMON_DATE)){
                returnValue = YESTERDAY + " ";
            }else{
                returnValue = THEDAY_BEFORE_YESTERDAY + " ";
            }
            returnValue += DateUtil.date2String(inDate, DatePattern.COMMON_TIME_WITHOUT_SECOND);
        }
        // 间隔2天
        else if (spaceDay == 2){
            if (DateUtil.isEquals(DateUtil.addDay(inDate, 2), nowDate, DatePattern.COMMON_DATE)){
                returnValue = THEDAY_BEFORE_YESTERDAY + " " + DateUtil.date2String(inDate, DatePattern.COMMON_TIME_WITHOUT_SECOND);
            }else{
                // 今年
                if (year == inYear){
                    returnValue = DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_YEAR_AND_SECOND);
                }else{
                    returnValue = DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_SECOND);
                }
            }
        }
        // 间隔大于2天
        else if (spaceDay > 2){
            // 今年
            if (year == inYear){
                returnValue = DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_YEAR_AND_SECOND);
            }else{
                returnValue = DateUtil.date2String(inDate, DatePattern.COMMON_DATE_AND_TIME_WITHOUT_SECOND);
            }
        }
        // 间隔0天
        else if (spaceDay == 0){
            // 小时间隔
            if (spaceHour != 0){
                if (inDay == day){
                    returnValue = spaceHour + HOUR + "前";
                }else{
                    returnValue = YESTERDAY + " " + DateUtil.date2String(inDate, DatePattern.COMMON_TIME_WITHOUT_SECOND);
                }
            }else{
                // 分钟间隔
                if (spaceMinute == 0){
                    returnValue = spaceSecond + SECOND + "前";
                }else{
                    returnValue = spaceMinute + MINUTE + "前";
                }
            }
        }
        return returnValue;
    }
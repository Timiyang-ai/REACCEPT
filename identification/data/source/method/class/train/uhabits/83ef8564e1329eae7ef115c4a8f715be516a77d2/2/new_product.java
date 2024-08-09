public double getTodayValue()
    {
        if(todayValue == null) todayValue = getValue(DateUtils.getStartOfToday());
        return todayValue;
    }
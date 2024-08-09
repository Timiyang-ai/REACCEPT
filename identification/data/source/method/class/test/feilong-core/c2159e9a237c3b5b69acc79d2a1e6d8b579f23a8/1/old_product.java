public static int getHourOfYear(Date date){
        Date firstDateOfThisYear = getFirstDateOfThisYear(date);
        return getIntervalHour(firstDateOfThisYear, date);
    }
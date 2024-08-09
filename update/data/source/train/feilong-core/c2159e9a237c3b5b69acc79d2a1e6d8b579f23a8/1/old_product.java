public static int getDayOfYear(Date date){
        Date firstDateOfThisYear = getFirstDateOfThisYear(date);
        return getIntervalDay(date, firstDateOfThisYear) + 1;
    }
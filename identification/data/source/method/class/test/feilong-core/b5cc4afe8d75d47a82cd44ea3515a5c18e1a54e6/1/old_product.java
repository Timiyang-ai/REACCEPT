public static Calendar resetYearEnd(Calendar calendar){
        Validate.notNull(calendar, "calendar can't be null!");
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        return resetDayEnd(calendar);
    }
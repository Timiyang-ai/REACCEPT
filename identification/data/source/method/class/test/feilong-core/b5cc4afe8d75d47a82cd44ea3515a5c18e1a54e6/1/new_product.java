public static Calendar resetYearEnd(Calendar calendar){
        Validate.notNull(calendar, "calendar can't be null!");
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return resetDayEnd(calendar);
    }
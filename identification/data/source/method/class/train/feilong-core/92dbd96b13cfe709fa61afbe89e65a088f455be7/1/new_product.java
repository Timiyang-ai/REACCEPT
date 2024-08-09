public static boolean isToday(Date date){
        Validate.notNull(date, "date can't be null!");
        return DateUtils.isSameDay(date, now());
    }
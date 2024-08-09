public int compareTo(DayOfMonth otherDayOfMonth) {
        return DateTimes.safeCompare(dayOfMonth, otherDayOfMonth.dayOfMonth);
    }
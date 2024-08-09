public JapaneseDate withMonthOfYear(int monthOfYear) {
        JapaneseChronology.monthOfYearRule().checkValue(monthOfYear);
        return JapaneseDate.japaneseDate(date.withMonthOfYear(monthOfYear));
    }
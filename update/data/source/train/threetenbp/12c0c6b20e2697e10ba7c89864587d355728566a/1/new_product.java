public JapaneseDate withMonthOfYear(MonthOfYear monthOfYear) {
        I18NUtil.checkNotNull(monthOfYear, "MonthOfYear must not be null");
        return JapaneseDate.japaneseDate(date.with(monthOfYear));
    }
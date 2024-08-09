public LocalDateTime withDate(int year, Month month, int dayOfMonth) {
        if (year == getYear() &&
                month == getMonthOfYear() &&
                dayOfMonth == getDayOfMonth()) {
            return this;
        }
        LocalDate newDate = LocalDate.of(year, month, dayOfMonth);
        return with(newDate, time);
    }
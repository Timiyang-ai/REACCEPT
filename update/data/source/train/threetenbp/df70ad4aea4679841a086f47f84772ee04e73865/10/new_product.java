public JapaneseDate plusDays(long days) {
        if (days == 0) {
            return this;
        }
        return JapaneseDate.from(date.plusDays(days));
    }
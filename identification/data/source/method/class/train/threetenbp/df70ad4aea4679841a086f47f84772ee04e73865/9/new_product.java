public JapaneseDate minusDays(long days) {
        if (days == 0) {
            return this;
        }
        return JapaneseDate.from(date.minusDays(days));
    }
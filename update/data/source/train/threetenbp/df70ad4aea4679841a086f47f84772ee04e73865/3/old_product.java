public JapaneseDate minusDays(long days) {
        if (days == 0) {
            return this;
        }
        return JapaneseDate.japaneseDate(date.minusDays(days));
    }
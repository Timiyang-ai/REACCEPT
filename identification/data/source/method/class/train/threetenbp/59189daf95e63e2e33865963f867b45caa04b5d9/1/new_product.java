public static MonthDay now(Clock clock) {
        final LocalDate now = LocalDate.now(clock);  // called once
        return MonthDay.of(now.getMonth(), now.getDayOfMonth());
    }
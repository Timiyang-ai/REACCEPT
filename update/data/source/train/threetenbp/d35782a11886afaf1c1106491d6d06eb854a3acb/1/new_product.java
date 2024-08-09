public static MonthDay from(CalendricalObject calendrical) {
        LocalDate date = LocalDate.from(calendrical);
        return MonthDay.of(date.getMonthOfYear(), date.getDayOfMonth());
    }
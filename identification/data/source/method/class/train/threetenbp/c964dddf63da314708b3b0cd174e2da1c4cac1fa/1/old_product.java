public ZoneOffsetTransition createTransition(int year) {
        LocalDate date;
        if (dom < 0) {
            date = LocalDate.of(year, month, month.length(ISOChrono.INSTANCE.isLeapYear(year)) + 1 + dom);
            if (dow != null) {
                date = date.with(previousOrCurrent(dow));
            }
        } else {
            date = LocalDate.of(year, month, dom);
            if (dow != null) {
                date = date.with(nextOrCurrent(dow));
            }
        }
        if (timeEndOfDay) {
            date = date.plusDays(1);
        }
        LocalDateTime localDT = LocalDateTime.of(date, time);
        OffsetDateTime transition = timeDefinition.createDateTime(localDT, standardOffset, offsetBefore);
        return new ZoneOffsetTransition(transition, offsetAfter);
    }
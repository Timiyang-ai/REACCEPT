public boolean matchesDate(LocalDate date) {
        ISOChronology.checkNotNull(date, "LocalDate must not be null");
        for (Entry<DateTimeFieldRule<?>, Integer> entry : fieldValueMap.entrySet()) {
            Integer dateValue = entry.getKey().getInteger(date);
            if (dateValue != null && dateValue.equals(entry.getValue()) == false) {
                return false;
            }
        }
        return true;
    }
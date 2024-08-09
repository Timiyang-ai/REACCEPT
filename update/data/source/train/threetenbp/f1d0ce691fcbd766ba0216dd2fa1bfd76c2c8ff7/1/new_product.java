public boolean matchesCalendrical(Calendrical calendrical) {
        ISOChronology.checkNotNull(calendrical, "Calendrical must not be null");
        for (Entry<DateTimeFieldRule<?>, Integer> entry : fieldValueMap.entrySet()) {
            Integer dateValue = entry.getKey().getInteger(calendrical);
            if (dateValue != null && dateValue.equals(entry.getValue()) == false) {
                return false;
            }
        }
        return true;
    }
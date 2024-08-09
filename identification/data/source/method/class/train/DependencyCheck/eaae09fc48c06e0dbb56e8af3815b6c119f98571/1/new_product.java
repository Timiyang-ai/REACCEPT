public static boolean withinDateRange(long date, long compareTo, int dayRange) {
        // ms = dayRange x 24 hours/day x 60 min/hour x 60 sec/min x 1000 ms/sec
        final long msRange = dayRange * 24L * 60L * 60L;
        return (compareTo - date) < msRange;
    }
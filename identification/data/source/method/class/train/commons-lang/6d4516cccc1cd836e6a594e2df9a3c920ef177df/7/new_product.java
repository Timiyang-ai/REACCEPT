public static FastDateFormat getInstance() {
        return cache.getDateTimeInstance(SHORT, SHORT, null, null);
    }
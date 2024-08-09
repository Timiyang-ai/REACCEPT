public static boolean isFuture(Long timestamp) {
        return timestamp != null && timestamp >  Calendar.getInstance().getTimeInMillis();
    }
public static boolean isFuture(long timestamp) {
        try {
            return timestamp >  Calendar.getInstance().getTimeInMillis();
        } catch (Exception e) {
            return false;
        }
    }
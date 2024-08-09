public static boolean isFuture(String timestamp) {
        try {
            return Long.parseLong(timestamp) >  Calendar.getInstance().getTimeInMillis();
        } catch (Exception e) {
            return false;
        }
    }
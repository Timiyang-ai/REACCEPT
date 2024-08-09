public static boolean isFuture(String timestamp) {
        return !TextUtils.isEmpty(timestamp) && isFuture(Long.parseLong(timestamp));
    }
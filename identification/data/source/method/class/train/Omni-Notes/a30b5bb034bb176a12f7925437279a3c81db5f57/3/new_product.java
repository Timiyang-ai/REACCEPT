public static boolean isFuture(String timestamp) {
        return !StringUtils.isEmpty(timestamp) && isFuture(Long.parseLong(timestamp));
    }
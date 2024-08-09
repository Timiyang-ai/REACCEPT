public static String formatAsPercentage(int value, int sum) {
        // value '1F' is to force floating conversion instead of loosing decimal part
        return PERCENT_FORMATTER.format(1F * value / sum);
    }
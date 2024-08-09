public static String formatAsPercentage(int value, int total) {
        // value '1F' is to force floating conversion instead of loosing decimal part
        float average = total == 0 ? 0 : 1F * value / total;
        return PERCENT_FORMATTER.format(average);
    }
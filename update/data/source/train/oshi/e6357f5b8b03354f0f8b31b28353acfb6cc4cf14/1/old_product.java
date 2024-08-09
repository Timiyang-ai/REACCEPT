public static long parseHertz(String hertz) {
        Matcher matcher = HERTZ.matcher(hertz.trim());
        if (matcher.find() && matcher.groupCount() == 3) {
            try {
                Double value = Double.valueOf(matcher.group(1)) * multipliers.getOrDefault(matcher.group(3), -1L);
                return value < 0 ? -1L : value.longValue();
            } catch (NumberFormatException e) {
                LOG.trace("", e);
            }
        }
        return -1L;
    }
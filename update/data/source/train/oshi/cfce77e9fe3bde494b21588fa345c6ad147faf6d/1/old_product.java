public static int getFirstIntValue(String line) {
        String[] split = line.split("=");
        if (split.length < 2) {
            return 0;
        }
        return parseIntOrDefault(split[1].trim().split("\\s+")[0], 0);
    }
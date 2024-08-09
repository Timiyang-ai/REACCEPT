@Override
    public String tagKey(String key) {
        String conventionKey = delegate.tagKey(key);

        conventionKey = START_UNDERSCORE_PATTERN.matcher(conventionKey).replaceAll(""); // 2
        conventionKey = SF_PATTERN.matcher(conventionKey).replaceAll(""); // 2

        if (!START_LETTERS_PATTERN.matcher(conventionKey).matches()) { // 3
            conventionKey = "a" + conventionKey;
        }
        return StringUtils.truncate(conventionKey, KEY_MAX_LENGTH); // 1
    }
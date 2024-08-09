@Override
    public String tagKey(String key) {
        String conventionKey = delegate.tagKey(key);

        conventionKey = START_UNDERSCORE_PATTERN.matcher(conventionKey).replaceAll(""); // 2
        conventionKey = SF_PATTERN.matcher(conventionKey).replaceAll(""); // 2

        conventionKey = PATTERN_TAG_KEY_BLACKLISTED_CHARS.matcher(conventionKey).replaceAll("_");
        if (!START_LETTERS_PATTERN.matcher(conventionKey).matches()) { // 3
            conventionKey = "a" + conventionKey;
        }
        if (PATTERN_TAG_KEY_BLACKLISTED_PREFIX.matcher(conventionKey).matches()) {
            logger.log("'" + conventionKey + "' (original name: '" + key + "') is not a valid tag key. "
                    + "Must not start with any of these prefixes: aws_, gcp_, or azure_. "
                    + "Please rename it to conform to the constraints. "
                    + "If it comes from a third party, please use MeterFilter to rename it.");
        }
        return StringUtils.truncate(conventionKey, KEY_MAX_LENGTH); // 1
    }
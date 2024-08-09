@Override
    public String tagKey(String key) {
        String delegatedTagKey = this.delegate.tagKey(key);
        return PATTERN_TAG_KEY_TO_SANITIZE.matcher(delegatedTagKey).replaceAll("_");
    }
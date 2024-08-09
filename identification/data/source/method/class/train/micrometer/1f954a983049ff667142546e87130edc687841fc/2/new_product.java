@Override
    public String tagValue(String value) {
        String sanitized = delegate.tagValue(value);
        return StringEscapeUtils.escapeJson(sanitized.endsWith("\\") ?
                sanitized.substring(0, sanitized.length() - 1) + "_" :
                sanitized);
    }
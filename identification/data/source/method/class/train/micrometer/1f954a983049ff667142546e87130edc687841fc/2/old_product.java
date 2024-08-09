@Override
    public String tagValue(String value) {
        String partiallySanitized = delegate.tagValue(value).replace("\"", "\\\"");
        if (partiallySanitized.endsWith("\\"))
            return partiallySanitized.substring(0, partiallySanitized.length() - 1) + "_";
        return partiallySanitized;
    }
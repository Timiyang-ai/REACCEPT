public StrBuilder appendSeparator(final String standard, final String defaultIfEmpty) {
        String str = isEmpty() ? defaultIfEmpty : standard;
        if (str != null) {
            append(str);
        }
        return this;
    }
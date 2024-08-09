@Override
    public String tagValue(String value) {
        // FIXME sanitize tag values of unacceptable characters
        return delegate.tagValue(value);
    }
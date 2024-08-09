@Override
    public String tagKey(String key) {
        // FIXME sanitize tag keys of unacceptable characters
        return delegate.tagKey(key);
    }
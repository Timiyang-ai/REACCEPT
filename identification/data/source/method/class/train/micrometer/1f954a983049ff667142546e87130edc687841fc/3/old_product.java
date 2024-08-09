@Override
    public String tagKey(String key) {
        return delegate.tagKey(key).replaceAll("[^a-zA-Z0-9\\-_\\.]", "_");
    }
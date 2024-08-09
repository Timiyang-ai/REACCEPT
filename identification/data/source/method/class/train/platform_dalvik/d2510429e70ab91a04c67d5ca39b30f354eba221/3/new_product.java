@Override
    public synchronized Set<SelectionKey> keys() {
        closeCheck();
        return unmodifiableKeys;
    }
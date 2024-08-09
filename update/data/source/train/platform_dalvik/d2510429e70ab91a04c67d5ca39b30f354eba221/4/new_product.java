@Override
    public synchronized Set<SelectionKey> selectedKeys() {
        closeCheck();
        return selectedKeys;
    }
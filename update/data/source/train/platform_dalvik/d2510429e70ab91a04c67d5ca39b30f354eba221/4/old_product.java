public synchronized Set<SelectionKey> selectedKeys() {
        closeCheck();
        return unaddableSelectedKeys;
    }
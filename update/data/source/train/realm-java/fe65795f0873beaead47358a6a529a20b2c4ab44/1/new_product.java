@Override
    public void add(int location, E object) {
        checkValidObject(object);
        if (managedMode) {
            checkValidView();
            if (location < 0 || location > size()) {
                throw new IndexOutOfBoundsException("Invalid index " + location + ", size is " + size());
            }
            RealmObjectProxy proxy = (RealmObjectProxy) copyToRealmIfNeeded(object);
            view.insert(location, proxy.realmGet$proxyState().getRow$realm().getIndex());
        } else {
            unmanagedList.add(location, object);
        }
        modCount++;
    }
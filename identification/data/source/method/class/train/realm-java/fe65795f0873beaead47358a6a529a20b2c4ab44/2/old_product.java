@Override
    public E set(int location, E object) {
        checkValidObject(object);
        E oldObject;
        if (managedMode) {
            checkValidView();
            RealmObjectProxy proxy = (RealmObjectProxy) copyToRealmIfNeeded(object);
            oldObject = get(location);
            view.set(location, proxy.realmGet$proxyState().getRow$realm().getIndex());
            return oldObject;
        } else {
            oldObject = nonManagedList.set(location, object);
        }
        return oldObject;
    }
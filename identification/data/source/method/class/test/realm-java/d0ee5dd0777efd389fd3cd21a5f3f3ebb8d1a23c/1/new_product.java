public static <E extends RealmModel> void removeAllChangeListeners(E object) {
        if (object instanceof RealmObjectProxy) {
            RealmObjectProxy proxy = (RealmObjectProxy) object;
            BaseRealm realm = proxy.realmGet$proxyState().getRealm$realm();
            if (realm.isClosed()) {
                RealmLog.warn("Calling removeChangeListener on a closed Realm %s, " +
                        "make sure to close all listeners before closing the Realm.", realm.configuration.getPath());
            }
            proxy.realmGet$proxyState().removeAllChangeListeners();
        } else {
            throw new IllegalArgumentException("Cannot remove listeners from this unmanaged RealmObject (created outside of Realm)");
        }
    }
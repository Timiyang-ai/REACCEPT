public static <E extends RealmModel> void removeAllChangeListeners(E object) {
        if (object instanceof RealmObjectProxy) {
            RealmObjectProxy proxy = (RealmObjectProxy) object;
            BaseRealm realm = proxy.realmGet$proxyState().getRealm$realm();
            realm.checkIfValid();
            realm.sharedRealm.capabilities.checkCanDeliverNotification(BaseRealm.LISTENER_NOT_ALLOWED_MESSAGE);
            proxy.realmGet$proxyState().removeAllChangeListeners();
        } else {
            throw new IllegalArgumentException("Cannot remove listeners from this unmanaged RealmObject (created outside of Realm)");
        }
    }
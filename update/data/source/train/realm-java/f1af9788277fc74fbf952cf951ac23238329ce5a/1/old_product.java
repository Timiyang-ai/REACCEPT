@Override
    public OrderedRealmCollectionSnapshot<E> createSnapshot() {
        if (!isManaged()) {
            throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
        }
        checkValidView();
        if (className != null) {
            return new OrderedRealmCollectionSnapshot<>(
                    realm,
                    new io.realm.internal.Collection(realm.sharedRealm, view, null),
                    className);
        } else {
            // 'clazz' is non-null when 'dynamicClassName' is null.
            //noinspection ConstantConditions
            return new OrderedRealmCollectionSnapshot<>(
                    realm,
                    new io.realm.internal.Collection(realm.sharedRealm, view, null),
                    clazz);
        }
    }
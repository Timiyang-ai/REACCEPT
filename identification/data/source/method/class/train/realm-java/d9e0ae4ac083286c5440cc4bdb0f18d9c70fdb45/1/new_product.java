@Override
    public OrderedRealmCollectionSnapshot<E> createSnapshot() {
        if (!isManaged()) {
            throw new UnsupportedOperationException(ONLY_IN_MANAGED_MODE_MESSAGE);
        }
        checkValidRealm();
        if (!osListOperator.forRealmModel()) {
            throw new IllegalStateException(ALLOWED_ONLY_FOR_REALM_MODEL_ELEMENT_MESSAGE);
        }
        if (className != null) {
            return new OrderedRealmCollectionSnapshot<>(
                    realm,
                    new io.realm.internal.Collection(realm.sharedRealm, osListOperator.getOsList(), null),
                    className);
        } else {
            // 'clazz' is non-null when 'dynamicClassName' is null.
            //noinspection ConstantConditions
            return new OrderedRealmCollectionSnapshot<>(
                    realm,
                    new io.realm.internal.Collection(realm.sharedRealm, osListOperator.getOsList(), null),
                    clazz);
        }
    }
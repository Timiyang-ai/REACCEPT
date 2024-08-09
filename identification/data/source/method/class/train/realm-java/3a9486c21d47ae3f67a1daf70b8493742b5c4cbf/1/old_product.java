public static boolean migratePrimaryKeyTableIfNeeded(SharedRealm sharedRealm) {
        if (sharedRealm == null || !sharedRealm.isInTransaction()) {
            throwImmutable();
        }
        if (!sharedRealm.hasTable(PRIMARY_KEY_TABLE_NAME)) {
            return false;
        }
        Table pkTable = sharedRealm.getTable(PRIMARY_KEY_TABLE_NAME);
        return nativeMigratePrimaryKeyTableIfNeeded(sharedRealm.getGroupNative(), pkTable.nativePtr);
    }
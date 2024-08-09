public static void migratePrimaryKeyTableIfNeeded(SharedRealm sharedRealm) {
        nativeMigratePrimaryKeyTableIfNeeded(sharedRealm.getNativePtr());
    }
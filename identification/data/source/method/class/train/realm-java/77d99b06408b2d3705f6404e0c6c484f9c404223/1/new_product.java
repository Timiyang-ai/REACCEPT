public static void migratePrimaryKeyTableIfNeeded(OsSharedRealm sharedRealm) {
        nativeMigratePrimaryKeyTableIfNeeded(sharedRealm.getNativePtr());
    }
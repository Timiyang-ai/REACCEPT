public static SyncUser currentUser() {
        SyncUser user = SyncManager.getUserStore().get();
        if (user != null && user.isValid()) {
            return user;
        }
        return null;
    }
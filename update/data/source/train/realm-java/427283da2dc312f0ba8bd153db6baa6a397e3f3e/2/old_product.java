public static SyncUser currentUser() {
        SyncUser user = SyncManager.getUserStore().getCurrent();
        if (user != null && user.isValid()) {
            return user;
        }
        return null;
    }
public static SyncUser currentUser() {
        SyncUser user = SyncManager.getUserStore().get(UserStore.CURRENT_USER_KEY);
        if (user != null && user.isValid()) {
            return user;
        }
        return null;
    }
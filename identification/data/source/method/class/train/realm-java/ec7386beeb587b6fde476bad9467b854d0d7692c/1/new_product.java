public static Map<String, SyncUser> all() {
        UserStore userStore = SyncManager.getUserStore();
        Collection<SyncUser> storedUsers = userStore.allUsers();
        Map<String, SyncUser> map = new HashMap<String, SyncUser>();
        for (SyncUser user : storedUsers) {
            if (user.isValid()) {
                map.put(user.getIdentity(), user);
            }
        }
        return Collections.unmodifiableMap(map);
    }
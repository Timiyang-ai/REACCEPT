public static Collection<SyncUser> all() {
        UserStore userStore = SyncManager.getUserStore();
        Collection<SyncUser> storedUsers = userStore.allUsers();
        List<SyncUser> result = new ArrayList<SyncUser>(storedUsers.size());
        for (SyncUser user : storedUsers) {
            if (user.isValid()) {
                result.add(user);
            }
        }
        return result;
    }
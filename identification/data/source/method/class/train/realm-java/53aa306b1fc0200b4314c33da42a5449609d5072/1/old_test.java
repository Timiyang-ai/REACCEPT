@Test
    public void currentUser_returnsNullIfUserExpired() {
        // Add an expired user to the user store
        UserStore userStore = new SharedPrefsUserStore(InstrumentationRegistry.getContext());
        SyncManager.setUserStore(userStore);
        userStore.put(UserStore.CURRENT_USER_KEY, SyncTestUtils.createTestUser(Long.MIN_VALUE));

        // Invalid users should not be returned when asking the for the current user
        assertNull(SyncUser.currentUser());
    }
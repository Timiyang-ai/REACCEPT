@Test
    public void currentUser_returnsNullIfUserExpired() {
        // Add an expired user to the user store
        UserStore userStore = SyncManager.getUserStore();
        userStore.put(SyncTestUtils.createTestUser(Long.MIN_VALUE));

        // Invalid users should not be returned when asking the for the current user
        assertNull(SyncUser.currentUser());
    }
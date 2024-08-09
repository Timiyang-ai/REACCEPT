@Test
    public void all_validUsers() {
        // Add 1 expired user and 1 valid user to the user store
        UserStore userStore = SyncManager.getUserStore();
        userStore.put(SyncTestUtils.createTestUser(Long.MIN_VALUE));
        userStore.put(SyncTestUtils.createTestUser(Long.MAX_VALUE));

        Collection<SyncUser> users = SyncUser.all();
        assertEquals(1, users.size());
        assertTrue(users.iterator().next().isValid());
    }
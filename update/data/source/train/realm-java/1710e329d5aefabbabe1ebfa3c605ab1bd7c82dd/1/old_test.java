@Test
    public void logout_sameSyncUserMultipleSessions() {
        SyncUser user = UserFactory.createUniqueUser(Constants.AUTH_URL);

        SyncConfiguration syncConfiguration1 = configFactory
                .createSyncConfigurationBuilder(user, Constants.SYNC_SERVER_URL)
                .build();
        Realm realm1 = Realm.getInstance(syncConfiguration1);

        SyncConfiguration syncConfiguration2 = configFactory
                .createSyncConfigurationBuilder(user, Constants.SYNC_SERVER_URL_2)
                .build();
        Realm realm2 = Realm.getInstance(syncConfiguration2);

        SyncSession session1 = SyncManager.getSession(syncConfiguration1);
        SyncSession session2 = SyncManager.getSession(syncConfiguration2);

        // make sure the `access_token` is acquired. otherwise we can still be
        // in WAITING_FOR_ACCESS_TOKEN state
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));

        assertNotEquals(session1, session2);
        assertEquals(SyncSession.State.ACTIVE, session1.getState());
        assertEquals(SyncSession.State.ACTIVE, session2.getState());
        assertEquals(session1.getUser(), session2.getUser());

        try {
            user.logout();
            fail("Should not be able to logout with two open Realm instances");
        } catch (IllegalStateException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.containsString("A Realm controlled by this user is still open. Close all Realms before logging out"));
        }

        realm1.close();

        try {
            user.logout();
            fail("Should not be able to logout with one open Realm instance");
        } catch (IllegalStateException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.containsString("A Realm controlled by this user is still open. Close all Realms before logging out"));
        }

        realm2.close();
        user.logout();
    }
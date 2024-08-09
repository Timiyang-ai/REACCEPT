@Test(timeout=5000)
    public void logout_sameSyncUserMultipleSessions() {
        String uniqueName = UUID.randomUUID().toString();
        SyncCredentials credentials = SyncCredentials.usernamePassword(uniqueName, "password", true);
        SyncUser user =  SyncUser.login(credentials, Constants.AUTH_URL);

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
        while(session1.getState() != SyncSession.State.ACTIVE || session2.getState() != SyncSession.State.ACTIVE) {
            SystemClock.sleep(200);
        }
        assertEquals(SyncSession.State.ACTIVE, session1.getState());
        assertEquals(SyncSession.State.ACTIVE, session2.getState());
        assertNotEquals(session1, session2);

        assertEquals(session1.getUser(), session2.getUser());

        user.logout();

        assertEquals(SyncSession.State.INACTIVE, session1.getState());
        assertEquals(SyncSession.State.INACTIVE, session2.getState());

        credentials = SyncCredentials.usernamePassword(uniqueName, "password", false);
        SyncUser.login(credentials, Constants.AUTH_URL);

        // reviving the sessions. The state could be changed concurrently.
        assertTrue(session1.getState() == SyncSession.State.WAITING_FOR_ACCESS_TOKEN ||
                session1.getState() == SyncSession.State.ACTIVE);
        assertTrue(session2.getState() == SyncSession.State.WAITING_FOR_ACCESS_TOKEN ||
                session2.getState() == SyncSession.State.ACTIVE);

        realm1.close();
        realm2.close();
    }
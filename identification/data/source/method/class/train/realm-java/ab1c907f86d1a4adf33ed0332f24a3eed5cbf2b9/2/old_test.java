@Test
    @RunTestInLooperThread
    public void findAllAsync_callerIsAdvanced() throws Throwable {
        final AtomicInteger numberOfIntercept = new AtomicInteger(0);
        final Realm realm = looperThread.realm;
        populateTestRealm(realm, 10);

        // Configure handler interceptor
        final Handler handler = new HandlerProxy(realm.handlerController) {
            @Override
            public boolean onInterceptInMessage(int what) {
                // Intercepts in order [QueryCompleted, RealmChanged, QueryUpdated]
                int intercepts = numberOfIntercept.incrementAndGet();
                switch (what) {
                    case HandlerController.COMPLETED_ASYNC_REALM_RESULTS: {
                        // we advance the Realm so we can simulate a retry
                        if (intercepts == 1) {
                            realm.beginTransaction();
                            realm.createObject(AllTypes.class).setColumnLong(0);
                            realm.commitTransaction();
                        }
                    }
                }
                return false;
            }
        };
        realm.setHandler(handler);

        // Create async query and verify it has not been loaded.
        final RealmResults<AllTypes> realmResults = realm.where(AllTypes.class)
                .between("columnLong", 0, 4)
                .findAllAsync();

        assertFalse(realmResults.isLoaded());
        assertEquals(0, realmResults.size());

        // Add change listener that should only be called once
        realmResults.addChangeListener(new RealmChangeListener<RealmResults<AllTypes>>() {
            @Override
            public void onChange(RealmResults<AllTypes> object) {
                assertEquals(3, numberOfIntercept.get());
                assertTrue(realmResults.isLoaded());
                assertEquals(6, realmResults.size());
                looperThread.testComplete();
            }
        });
    }
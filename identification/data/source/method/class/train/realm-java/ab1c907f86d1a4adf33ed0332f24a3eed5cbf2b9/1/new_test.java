@Test
    @RunTestInLooperThread
    public void findAllAsync_retry() throws Throwable {
        final AtomicInteger numberOfIntercept = new AtomicInteger(0);
        final AtomicInteger numberOfInvocation = new AtomicInteger(0);
        final Realm realm = looperThread.realm;

        // 1. Populate initial data
        realm.setAutoRefresh(false);
        populateTestRealm(realm, 10);
        realm.setAutoRefresh(true);

        // 2. Configure handler interceptor
        final Handler handler = new HandlerProxy(realm.handlerController) {
            @Override
            public boolean onInterceptInMessage(int what) {
                // Intercepts in order: [QueryComplete, RealmChanged, QueryUpdated]
                int intercepts = numberOfIntercept.incrementAndGet();
                switch (what) {
                    // 5. Intercept all messages from other threads. On the first complete, we advance the tread
                    // which will cause the async query to rerun instead of triggering the change listener.
                    case HandlerControllerConstants.COMPLETED_ASYNC_REALM_RESULTS:
                        if (intercepts == 1) {
                            // We advance the Realm so we can simulate a retry
                            realm.beginTransaction();
                            realm.delete(AllTypes.class);
                            realm.commitTransaction();
                        }
                }
                return false;
            }
        };
        realm.setHandler(handler);

        // 3. Create a async query
        final RealmResults<AllTypes> realmResults = realm.where(AllTypes.class)
                .between("columnLong", 0, 4)
                .findAllAsync();

        // 4. Ensure that query isn't loaded yet
        assertFalse(realmResults.isLoaded());
        assertEquals(0, realmResults.size());

        // 6. Callback triggered after retry has completed
        realmResults.addChangeListener(new RealmChangeListener<RealmResults<AllTypes>>() {
            @Override
            public void onChange(RealmResults<AllTypes> object) {
                assertEquals(3, numberOfIntercept.get());
                assertEquals(1, numberOfInvocation.incrementAndGet());
                assertTrue(realmResults.isLoaded());
                assertEquals(0, realmResults.size());
                looperThread.testComplete();
            }
        });
    }
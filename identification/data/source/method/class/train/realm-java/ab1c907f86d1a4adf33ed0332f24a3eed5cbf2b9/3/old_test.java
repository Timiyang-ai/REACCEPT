@Test
    @RunTestInLooperThread
    public void findAllSortedAsync_retry() throws Throwable {
        final AtomicInteger numberOfIntercept = new AtomicInteger(0);
        final Realm realm = looperThread.realm;

        // 1. Populate the Realm without triggering a RealmChangeEvent.
        realm.setAutoRefresh(false);
        populateTestRealm(realm, 10);
        realm.setAutoRefresh(true);

        // 2. Configure proxy handler to intercept messages
        final Handler handler = new HandlerProxy(realm.handlerController) {
            @Override
            public boolean onInterceptInMessage(int what) {
                // In order [QueryCompleted, RealmChanged, QueryUpdated]
                int intercepts = numberOfIntercept.incrementAndGet();
                switch (what) {
                    case HandlerController.COMPLETED_ASYNC_REALM_RESULTS: {
                        if (intercepts == 1) {
                            // We advance the Realm so we can simulate a retry before listeners are
                            // called.
                            realm.beginTransaction();
                            realm.where(AllTypes.class).equalTo(AllTypes.FIELD_LONG, 8).findFirst().deleteFromRealm();
                            realm.commitTransaction();
                        }
                        break;
                    }
                }
                return false;
            }
        };
        realm.setHandler(handler);

        // 3. This will add a task to the paused asyncTaskExecutor
        final RealmResults<AllTypes> realmResults = realm.where(AllTypes.class)
                .between("columnLong", 4, 8)
                .findAllSortedAsync("columnString", Sort.ASCENDING);

        assertFalse(realmResults.isLoaded());
        assertEquals(0, realmResults.size());

        // 4. Intercepting the query completed event the first time will
        // cause a commit that should cause the findAllSortedAsync to be re-run.
        // This change listener should only be called with the final result.
        realmResults.addChangeListener(new RealmChangeListener<RealmResults<AllTypes>>() {
            @Override
            public void onChange(RealmResults<AllTypes> object) {
                assertEquals(3, numberOfIntercept.get());
                looperThread.testComplete();
            }
        });
    }
@Test
    @RunTestInLooperThread
    public void findFirstAsync_retry() throws Throwable {
        final AtomicInteger numberOfIntercept = new AtomicInteger(0);
        final Realm realm = looperThread.realm;
        populateTestRealm(realm, 10);

        // Configure interceptor handler
        final Handler handler = new HandlerProxy(realm.handlerController) {
            @Override
            public boolean onInterceptInMessage(int what) {
                int intercepts = numberOfIntercept.incrementAndGet();
                switch (what) {
                    case HandlerControllerConstants.COMPLETED_ASYNC_REALM_OBJECT: {
                        if (intercepts == 1) {
                            // we advance the Realm so we can simulate a retry
                            realm.beginTransaction();
                            realm.delete(AllTypes.class);
                            AllTypes object = realm.createObject(AllTypes.class);
                            object.setColumnString("The Endless River");
                            object.setColumnLong(5);
                            realm.commitTransaction();
                        }
                    }
                }
                return false;
            }
        };
        realm.setHandler(handler);

        // Create a async query and verify it is not still loaded.
        final AllTypes realmResults = realm.where(AllTypes.class)
                .between("columnLong", 4, 6)
                .findFirstAsync();

        assertFalse(realmResults.isLoaded());

        try {
            realmResults.getColumnString();
            fail("Accessing property on an empty row");
        } catch (IllegalStateException ignored) {
        }

        // Add change listener that should only be called once after the retry completed.
        realmResults.addChangeListener(new RealmChangeListener<AllTypes>() {
            @Override
            public void onChange(AllTypes object) {
                assertEquals(3, numberOfIntercept.get());
                assertTrue(realmResults.isLoaded());
                assertEquals(5, realmResults.getColumnLong());
                assertEquals("The Endless River", realmResults.getColumnString());
                looperThread.testComplete();
            }
        });
    }
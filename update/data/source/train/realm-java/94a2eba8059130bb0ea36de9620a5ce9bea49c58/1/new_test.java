@Test
    @RunTestInLooperThread
    public void sort_async() throws Throwable {
        final Realm realm = looperThread.getRealm();
        populateTestRealm(realm, 10);

        final RealmResults<AllTypes> results = realm.where(AllTypes.class)
                .between("columnLong", 0, 4)
                .sort("columnString", Sort.DESCENDING)
                .findAllAsync();

        assertFalse(results.isLoaded());
        assertEquals(0, results.size());

        looperThread.keepStrongReference(results);
        results.addChangeListener(new RealmChangeListener<RealmResults<AllTypes>>() {
            @Override
            public void onChange(RealmResults<AllTypes> object) {
                assertTrue(results.isLoaded());
                assertEquals(5, results.size());
                for (int i = 0; i < 5; i++) {
                    int iteration = (4 - i);
                    assertEquals("test data " + iteration, results.get(4 - iteration).getColumnString());
                }
                looperThread.testComplete();
            }
        });
    }
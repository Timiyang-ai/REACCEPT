    @Test
    public void observeChangesInTables_shouldReceiveIfTableWasChanged() {
        TestSubscriber<Changes> testSubscriber = new TestSubscriber<Changes>();

        Set<String> tables = new HashSet<String>(2);
        tables.add("table1");
        tables.add("table2");

        storIOSQLite
                .observeChangesInTables(tables, LATEST)
                .subscribe(testSubscriber);

        testSubscriber.assertNoValues();

        Changes changes = Changes.newInstance("table2");

        storIOSQLite
                .lowLevel()
                .notifyAboutChanges(changes);

        testSubscriber.assertValues(changes);
        testSubscriber.assertNoErrors();
        testSubscriber.dispose();
    }
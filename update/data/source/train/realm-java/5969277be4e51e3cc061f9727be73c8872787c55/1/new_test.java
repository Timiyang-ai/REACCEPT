@Test
    @RunTestInLooperThread
    public void executeTransactionAsync_exceptionHandling() throws Throwable {
        final TestHelper.TestLogger testLogger = new TestHelper.TestLogger(LogLevel.DEBUG);
        RealmLog.add(testLogger);

        final Realm realm = looperThread.realm;

        assertEquals(0, realm.where(Owner.class).count());

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Owner owner = realm.createObject(Owner.class);
                owner.setName("Owner");
                realm.cancelTransaction(); // Cancel the transaction then throw
                throw new RuntimeException("Boom");
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                fail("Should not reach success if runtime exception is thrown in callback.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Ensure we are giving developers quality messages in the logs.
                assertEquals("Could not cancel transaction, not currently in a transaction.", testLogger.message);
                RealmLog.remove(testLogger);
                looperThread.testComplete();
            }
        });
    }
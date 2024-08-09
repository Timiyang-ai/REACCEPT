@Test
    @RunTestInLooperThread
    public void executeTransactionAsync() throws Throwable {
        assertEquals(0, looperThread.realm.allObjects(Owner.class).size());

        looperThread.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Owner owner = realm.createObject(Owner.class);
                owner.setName("Owner");
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                assertEquals(1, looperThread.realm.allObjects(Owner.class).size());
                assertEquals("Owner", looperThread.realm.where(Owner.class).findFirst().getName());
                looperThread.testComplete();
            }
        }, new Realm.Transaction.OnError() {

            @Override
            public void onError(Throwable error) {
                fail(error.getMessage());
            }
        });
    }
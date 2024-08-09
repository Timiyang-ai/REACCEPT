@Test
    @RunTestInLooperThread
    public void executeTransaction_async() throws Throwable {
        assertEquals(0, looperThread.realm.allObjects(Owner.class).size());

        looperThread.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Owner owner = realm.createObject(Owner.class);
                owner.setName("Owner");
            }
        }, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                assertEquals(1, looperThread.realm.allObjects(Owner.class).size());
                assertEquals("Owner", looperThread.realm.where(Owner.class).findFirst().getName());
                looperThread.testComplete();
            }

            @Override
            public void onError(Exception e) {
                looperThread.testComplete();;
                fail(e.getMessage());
            }
        });
    }
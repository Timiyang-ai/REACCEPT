@Test
    @RunTestInLooperThread
    public void executeTransactionAsync_callbacksShouldBeClearedBeforeCalling()
            throws NoSuchFieldException, IllegalAccessException {
        final AtomicInteger callbackCounter = new AtomicInteger(0);
        final Realm foregroundRealm = looperThread.getRealm();

        // Use single thread executor
        TestHelper.replaceRealmThreadExecutor(RealmThreadPoolExecutor.newSingleThreadExecutor());

        // To reproduce the issue, the posted callback needs to arrived before the Object Store did_change called.
        // We just disable the auto refresh here then the did_change won't be called.
        foregroundRealm.setAutoRefresh(false);
        foregroundRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObject(AllTypes.class);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // This will be called first and only once
                assertEquals(0, callbackCounter.getAndIncrement());

                // This transaction should never trigger the onSuccess.
                foregroundRealm.beginTransaction();
                foregroundRealm.createObject(AllTypes.class);
                foregroundRealm.commitTransaction();
            }
        });

        foregroundRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObject(AllTypes.class);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // This will be called 2nd and only once
                assertEquals(1, callbackCounter.getAndIncrement());
                looperThread.testComplete();
            }
        });

        // Wait for all async tasks finish to ensure the async transaction posted callback will arrive first.
        TestHelper.resetRealmThreadExecutor();
        looperThread.postRunnable(new Runnable() {
            @Override
            public void run() {
                // Manually call refresh, so the did_change will be triggered.
                foregroundRealm.sharedRealm.refresh();
            }
        });
    }
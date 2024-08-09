public RealmAsyncTask executeTransaction(final Transaction transaction, final Transaction.Callback callback) {
        if (transaction == null)
            throw new IllegalArgumentException("Transaction should not be null");

        // If the user provided a Callback then we make sure, the current Realm has a Handler
        // we can use to deliver the result
        if (callback != null && handler == null) {
            throw new IllegalStateException("Your Realm is opened from a thread without a Looper" +
                    " and you provided a callback, we need a Handler to invoke your callback");
        }

        // We need to use the same configuration to open a background SharedGroup (i.e Realm)
        // to perform the transaction
        final RealmConfiguration realmConfiguration = getConfiguration();

        final Future<?> pendingQuery = asyncQueryExecutor.submit(new Runnable() {
            @Override
            public void run() {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }

                boolean transactionCommitted = false;
                final Exception[] exception = new Exception[1];
                final Realm bgRealm = Realm.getInstance(realmConfiguration);
                bgRealm.beginTransaction();
                try {
                    transaction.execute(bgRealm);

                    if (!Thread.currentThread().isInterrupted()) {
                        bgRealm.commitTransaction(new Runnable() {
                            @Override
                            public void run() {
                                // The bgRealm needs to be closed before post event to caller's handler to avoid
                                // concurrency problem. eg.: User wants to delete Realm in the callbacks.
                                // This will close Realm before sending REALM_CHANGED.
                                bgRealm.close();
                            }
                        });
                        transactionCommitted = true;
                    }
                } catch (final Exception e) {
                    exception[0] = e;
                } finally {
                    if (!bgRealm.isClosed()) {
                        if (bgRealm.isInTransaction()) {
                            bgRealm.cancelTransaction();
                        } else if (exception[0] != null) {
                            RealmLog.w("Could not cancel transaction, not currently in a transaction.");
                        }
                        bgRealm.close();
                    }

                    // Send response as the final step to ensure the bg thread quit before others get the response!
                    if (callback != null
                            && handler != null
                            && !Thread.currentThread().isInterrupted()
                            && handler.getLooper().getThread().isAlive()) {
                        if (transactionCommitted) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onSuccess();
                                }
                            });
                        } else if (exception[0] != null) {
                            // transaction has not been canceled by there is a exception during transaction.
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onError(exception[0]);
                                }
                            });
                        }
                    }
                }
            }
        });

        return new RealmAsyncTask(pendingQuery);
    }
public RealmAsyncTask executeTransactionAsync(final Transaction transaction,
            final Realm.Transaction.OnSuccess onSuccess,
            final Realm.Transaction.OnError onError) {
        checkIfValid();

        if (transaction == null) {
            throw new IllegalArgumentException("Transaction should not be null");
        }

        // Avoid to call canDeliverNotification() in bg thread.
        final boolean canDeliverNotification = sharedRealm.capabilities.canDeliverNotification();

        // If the user provided a Callback then we have to make sure the current Realm has an events looper to deliver
        // the results.
        if ((onSuccess != null || onError != null)) {
            sharedRealm.capabilities.checkCanDeliverNotification("Callback cannot be delivered on current thread.");
        }

        // We need to use the same configuration to open a background SharedRealm (i.e Realm)
        // to perform the transaction
        final RealmConfiguration realmConfiguration = getConfiguration();
        // We need to deliver the callback even if the Realm is closed. So acquire a reference to the notifier here.
        final RealmNotifier realmNotifier = sharedRealm.realmNotifier;

        final Future<?> pendingTransaction = asyncTaskExecutor.submitTransaction(new Runnable() {
            @Override
            public void run() {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }

                SharedRealm.VersionID versionID = null;
                Throwable exception = null;

                final Realm bgRealm = Realm.getInstance(realmConfiguration);
                bgRealm.beginTransaction();
                try {
                    transaction.execute(bgRealm);

                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }

                    bgRealm.commitTransaction();
                    // The bgRealm needs to be closed before post event to caller's handler to avoid concurrency
                    // problem. This is currently guaranteed by posting callbacks later below.
                    versionID = bgRealm.sharedRealm.getVersionID();
                } catch (final Throwable e) {
                    exception = e;
                } finally {
                    try {
                        if (bgRealm.isInTransaction()) {
                            bgRealm.cancelTransaction();
                        }
                    } finally {
                        bgRealm.close();
                    }
                }

                final Throwable backgroundException = exception;
                final SharedRealm.VersionID backgroundVersionID = versionID;
                // Cannot be interrupted anymore.
                if (canDeliverNotification) {
                    if (backgroundVersionID != null && onSuccess != null) {
                        realmNotifier.post(new Runnable() {
                            @Override
                            public void run() {
                                if (isClosed()) {
                                    // The caller Realm is closed. Just call the onSuccess. Since the new created Realm
                                    // cannot be behind the background one.
                                    onSuccess.onSuccess();
                                    return;
                                }

                                if (sharedRealm.getVersionID().compareTo(backgroundVersionID) < 0) {
                                    sharedRealm.realmNotifier.addTransactionCallback(new Runnable() {
                                        @Override
                                        public void run() {
                                            onSuccess.onSuccess();
                                        }
                                    });
                                } else {
                                    onSuccess.onSuccess();
                                }
                            }
                        });
                    } else if (backgroundException != null) {
                        realmNotifier.post(new Runnable() {
                            @Override
                            public void run() {
                                if (onError != null) {
                                    onError.onError(backgroundException);
                                } else {
                                    throw new RealmException("Async transaction failed", backgroundException);
                                }
                            }
                        });
                    }
                } else {
                    if (backgroundException != null) {
                        // FIXME: ThreadPoolExecutor will never throw the exception in the background.
                        // We need a redesign of the async transaction API.
                        // Throw in the worker thread since the caller thread cannot get notifications.
                        throw new RealmException("Async transaction failed", backgroundException);
                    }
                }

            }
        });

        return new RealmAsyncTaskImpl(pendingTransaction, asyncTaskExecutor);
    }
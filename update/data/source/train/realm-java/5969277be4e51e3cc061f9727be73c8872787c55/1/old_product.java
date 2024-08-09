public RealmAsyncTask executeTransactionAsync(final Transaction transaction, final Realm.Transaction.OnSuccess onSuccess, final Realm.Transaction.OnError onError) {
        checkIfValid();

        if (transaction == null) {
            throw new IllegalArgumentException("Transaction should not be null");
        }

        // If the user provided a Callback then we make sure, the current Realm has a Handler
        // we can use to deliver the result
        if ((onSuccess != null || onError != null)  && !hasValidNotifier()) {
            throw new IllegalStateException("Your Realm is opened from a thread without a Looper" +
                    " and you provided a callback, we need a Handler to invoke your callback");
        }

        // We need to use the same configuration to open a background SharedRealm (i.e Realm)
        // to perform the transaction
        final RealmConfiguration realmConfiguration = getConfiguration();

        final Future<?> pendingTransaction = asyncTaskExecutor.submitTransaction(new Runnable() {
            @Override
            public void run() {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }

                boolean transactionCommitted = false;
                final Throwable[] exception = new Throwable[1];
                final Realm bgRealm = Realm.getInstance(realmConfiguration);
                bgRealm.beginTransaction();
                try {
                    transaction.execute(bgRealm);

                    if (!Thread.currentThread().isInterrupted()) {
                        // No need to send change notification to the work thread.
                        bgRealm.commitTransaction(false);
                        // The bgRealm needs to be closed before post event to caller's handler to avoid concurrency
                        // problem. This is currently guaranteed by posting handleAsyncTransactionCompleted below.
                        bgRealm.close();
                        transactionCommitted = true;
                    }
                } catch (final Throwable e) {
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

                    final Throwable backgroundException = exception[0];
                    // Send response as the final step to ensure the bg thread quit before others get the response!
                    if (hasValidNotifier() && !Thread.currentThread().isInterrupted()) {

                        if (transactionCommitted) {
                            // This will be treated like a special REALM_CHANGED event
                            sharedRealm.realmNotifier.post(new Runnable() {
                                @Override
                                public void run() {
                                    handlerController.handleAsyncTransactionCompleted(onSuccess != null ? new Runnable() {
                                        @Override
                                        public void run() {
                                            onSuccess.onSuccess();
                                        }
                                    } : null);
                                }
                            });
                        }

                        // Send errors directly to the looper, so they don't get intercepted by the HandlerController.
                        if (backgroundException != null) {
                            if (onError != null) {
                                sharedRealm.realmNotifier.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        onError.onError(backgroundException);
                                    }
                                });
                            } else {
                                sharedRealm.realmNotifier.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (backgroundException instanceof RuntimeException) {
                                            throw (RuntimeException) backgroundException;
                                        } else if (backgroundException instanceof Exception) {
                                            throw new RealmException("Async transaction failed", backgroundException);
                                        } else if (backgroundException instanceof Error) {
                                            throw (Error) backgroundException;
                                        }
                                    }
                                });
                            }
                        }

                    } else {
                        // Throw exception in the worker thread if the caller thread terminated
                        if (backgroundException != null) {
                            if (backgroundException instanceof RuntimeException) {
                                //noinspection ThrowFromFinallyBlock
                                throw (RuntimeException) backgroundException;
                            } else if (backgroundException instanceof Exception) {
                                //noinspection ThrowFromFinallyBlock
                                throw new RealmException("Async transaction failed", backgroundException);
                            } else if (backgroundException instanceof Error) {
                                //noinspection ThrowFromFinallyBlock
                                throw (Error) backgroundException;
                            }
                        }
                    }
                }
            }
        });

        return new RealmAsyncTask(pendingTransaction);
    }
public RealmResults<E> findAllAsync() {
        checkQueryIsNotReused();
        final WeakReference<Handler> weakHandler = getWeakReferenceHandler();

        // handover the query (to be used by a worker thread)
        final long handoverQueryPointer = query.handoverQuery(realm.sharedGroupManager.getNativePointer());

        // save query arguments (for future update)
        argumentsHolder = new ArgumentsHolder(ArgumentsHolder.TYPE_FIND_ALL);

        // we need to use the same configuration to open a background SharedGroup (i.e Realm)
        // to perform the query
        final RealmConfiguration realmConfiguration = realm.getConfiguration();

        // prepare an empty reference of the RealmResults, so we can return it immediately (promise)
        // then update it once the query completes in the background.
        RealmResults<E> realmResults;
        if (isDynamicQuery()) {
            //noinspection unchecked
            realmResults = (RealmResults<E>) RealmResults.createFromDynamicClass(realm, query, className);
        } else {
            realmResults = RealmResults.createFromTableQuery(realm, query, clazz);
        }

        final WeakReference<RealmResults<? extends RealmModel>> weakRealmResults = realm.handlerController.addToAsyncRealmResults(realmResults, this);

        final Future<Long> pendingQuery = Realm.asyncTaskExecutor.submitQuery(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                if (!Thread.currentThread().isInterrupted()) {
                    SharedGroup sharedGroup = null;

                    try {
                        sharedGroup = new SharedGroup(realmConfiguration.getPath(),
                                SharedGroup.IMPLICIT_TRANSACTION,
                                realmConfiguration.getDurability(),
                                realmConfiguration.getEncryptionKey());

                        // Run the query & handover the table view for the caller thread
                        // Note: the handoverQueryPointer contains the versionID needed by the SG in order
                        // to import it.
                        long handoverTableViewPointer = query.findAllWithHandover(sharedGroup.getNativePointer(), sharedGroup.getNativeReplicationPointer(), handoverQueryPointer);

                        QueryUpdateTask.Result result = QueryUpdateTask.Result.newRealmResultsResponse();
                        result.updatedTableViews.put(weakRealmResults, handoverTableViewPointer);
                        result.versionID = sharedGroup.getVersion();
                        closeSharedGroupAndSendMessageToHandler(sharedGroup,
                                weakHandler, HandlerControllerConstants.COMPLETED_ASYNC_REALM_RESULTS, result);

                        return handoverTableViewPointer;

                    } catch (BadVersionException e) {
                        // In some rare race conditions, this can happen. In that case, just ignore the error.
                        RealmLog.d("findAllAsync handover could not complete due to a BadVersionException. " +
                                "Retry is scheduled by a REALM_CHANGED event.");

                    } catch (Throwable e) {
                        RealmLog.e(e.getMessage(), e);
                        closeSharedGroupAndSendMessageToHandler(sharedGroup,
                                weakHandler, HandlerControllerConstants.REALM_ASYNC_BACKGROUND_EXCEPTION, new Error(e));

                    } finally {
                        if (sharedGroup != null && !sharedGroup.isClosed()) {
                            sharedGroup.close();
                        }
                    }
                } else {
                    TableQuery.nativeCloseQueryHandover(handoverQueryPointer);
                }

                return INVALID_NATIVE_POINTER;
            }
        });

        realmResults.setPendingQuery(pendingQuery);
        return realmResults;
    }
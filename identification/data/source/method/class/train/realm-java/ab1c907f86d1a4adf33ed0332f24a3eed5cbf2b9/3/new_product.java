public RealmResults<E> findAllSortedAsync(String fieldNames[], final Sort[] sortOrders) {
        checkQueryIsNotReused();
        checkSortParameters(fieldNames, sortOrders);

        if (fieldNames.length == 1 && sortOrders.length == 1) {
            return findAllSortedAsync(fieldNames[0], sortOrders[0]);

        } else {
            final WeakReference<Handler> weakHandler = getWeakReferenceHandler();

            // Handover the query (to be used by a worker thread)
            final long handoverQueryPointer = query.handoverQuery(realm.sharedGroupManager.getNativePointer());

            // We need to use the same configuration to open a background SharedGroup to perform the query
            final RealmConfiguration realmConfiguration = realm.getConfiguration();

            final long indices[] = new long[fieldNames.length];
            for (int i = 0; i < fieldNames.length; i++) {
                String fieldName = fieldNames[i];
                long columnIndex = getColumnIndexForSort(fieldName);
                indices[i] = columnIndex;
            }

            // capture the query arguments for future retries & update
            argumentsHolder = new ArgumentsHolder(ArgumentsHolder.TYPE_FIND_ALL_MULTI_SORTED);
            argumentsHolder.sortOrders = sortOrders;
            argumentsHolder.columnIndices = indices;

            // prepare the promise result
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

                            // run the query & handover the table view for the caller thread
                            long handoverTableViewPointer = query.findAllMultiSortedWithHandover(sharedGroup.getNativePointer(),
                                    sharedGroup.getNativeReplicationPointer(), handoverQueryPointer, indices, sortOrders);

                            QueryUpdateTask.Result result = QueryUpdateTask.Result.newRealmResultsResponse();
                            result.updatedTableViews.put(weakRealmResults, handoverTableViewPointer);
                            result.versionID = sharedGroup.getVersion();
                            closeSharedGroupAndSendMessageToHandler(sharedGroup,
                                    weakHandler, HandlerControllerConstants.COMPLETED_ASYNC_REALM_RESULTS, result);

                            return handoverTableViewPointer;
                        } catch (BadVersionException e) {
                            // In some rare race conditions, this can happen. In that case, just ignore the error.
                            RealmLog.d("findAllSortedAsync handover could not complete due to a BadVersionException. " +
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
    }
public E findFirstAsync() {
        checkQueryIsNotReused();
        final WeakReference<Handler> weakHandler = getWeakReferenceHandler();

        // handover the query (to be used by a worker thread)
        final long handoverQueryPointer = query.handoverQuery(realm.sharedGroupManager.getNativePointer());

        // save query arguments (for future update)
        argumentsHolder = new ArgumentsHolder(ArgumentsHolder.TYPE_FIND_FIRST);

        final RealmConfiguration realmConfiguration = realm.getConfiguration();

        // prepare an empty reference of the RealmObject, so we can return it immediately (promise)
        // then update it once the query complete in the background.
        final E result;
        if (isDynamicQuery()) {
            //noinspection unchecked
            result = (E) new DynamicRealmObject(className);
        } else {
            result = realm.getConfiguration().getSchemaMediator().newInstance(clazz, realm.getSchema().getColumnInfo(clazz));
        }

        RealmObjectProxy proxy = (RealmObjectProxy) result;
        final WeakReference<RealmObjectProxy> realmObjectWeakReference = realm.handlerController.addToAsyncRealmObject(proxy, this);
        proxy.realmGet$proxyState().setRealm$realm(realm);
        proxy.realmGet$proxyState().setRow$realm(Row.EMPTY_ROW);

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

                        long handoverRowPointer = query.findWithHandover(sharedGroup.getNativePointer(),
                                sharedGroup.getNativeReplicationPointer(), handoverQueryPointer);
                        if (handoverRowPointer == 0) { // empty row
                            realm.handlerController.addToEmptyAsyncRealmObject(realmObjectWeakReference, RealmQuery.this);
                            realm.handlerController.removeFromAsyncRealmObject(realmObjectWeakReference);
                        }

                        QueryUpdateTask.Result result = QueryUpdateTask.Result.newRealmObjectResponse();
                        result.updatedRow.put(realmObjectWeakReference, handoverRowPointer);
                        result.versionID = sharedGroup.getVersion();
                        closeSharedGroupAndSendMessageToHandler(sharedGroup,
                                weakHandler, HandlerController.COMPLETED_ASYNC_REALM_OBJECT, result);

                        return handoverRowPointer;

                    } catch (Exception e) {
                        RealmLog.e(e.getMessage(), e);
                        // handler can't throw a checked exception need to wrap it into unchecked Exception
                        closeSharedGroupAndSendMessageToHandler(sharedGroup,
                                weakHandler, HandlerController.REALM_ASYNC_BACKGROUND_EXCEPTION, new Error(e));

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
        proxy.realmGet$proxyState().setPendingQuery$realm(pendingQuery);

        return result;
    }
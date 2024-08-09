public long max() {
        return (Long) query.callInReadTx(new Callable<Long>() {
            @Override
            public Long call() {
                return nativeMax(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
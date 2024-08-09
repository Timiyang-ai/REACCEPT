public long sum() {
        return (Long) query.callInReadTx(new Callable<Long>() {
            @Override
            public Long call() {
                return query.nativeSum(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
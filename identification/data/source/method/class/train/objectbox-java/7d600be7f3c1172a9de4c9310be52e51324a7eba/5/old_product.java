public long min() {
        return (Long) query.callInReadTx(new Callable<Long>() {
            @Override
            public Long call() {
                return query.nativeMin(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
public double minDouble() {
        return (Double) query.callInReadTx(new Callable<Double>() {
            @Override
            public Double call() {
                return query.nativeMinDouble(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
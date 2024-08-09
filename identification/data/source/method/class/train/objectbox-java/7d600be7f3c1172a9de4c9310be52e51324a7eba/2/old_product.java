public double sumDouble() {
        return (Double) query.callInReadTx(new Callable<Double>() {
            @Override
            public Double call() {
                return query.nativeSumDouble(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
public double minDouble() {
        return (Double) query.callInReadTx(new Callable<Double>() {
            @Override
            public Double call() {
                return nativeMinDouble(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
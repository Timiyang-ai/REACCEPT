public double maxDouble() {
        return (Double) query.callInReadTx(new Callable<Double>() {
            @Override
            public Double call() {
                return nativeMaxDouble(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
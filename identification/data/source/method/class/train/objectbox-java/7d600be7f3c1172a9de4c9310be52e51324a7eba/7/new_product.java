public double avg() {
        return (Double) query.callInReadTx(new Callable<Double>() {
            @Override
            public Double call() {
                return nativeAvg(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
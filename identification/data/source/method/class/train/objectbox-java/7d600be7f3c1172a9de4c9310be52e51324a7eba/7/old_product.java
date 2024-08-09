public double avg() {
        return (Double) query.callInReadTx(new Callable<Double>() {
            @Override
            public Double call() {
                return query.nativeAvg(queryHandle, query.cursorHandle(), propertyId);
            }
        });
    }
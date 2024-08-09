public void addOpsServerLoad(int registeredUsersCount, int processedRequestCount, int deltaCalculationCount) {
        removeOldHistory();
        OperationsServerLoad snap = new OperationsServerLoad();
        snap.setRegisteredUsersCount(registeredUsersCount);
        snap.setProcessedRequestCount(processedRequestCount);
        snap.setDeltaCalculationCount(deltaCalculationCount);
        history.add(snap);
    }
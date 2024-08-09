public void addOpsServerLoad(LoadInfo load) {
        removeOldHistory();
        history.add(new OperationsServerLoad(load));
    }
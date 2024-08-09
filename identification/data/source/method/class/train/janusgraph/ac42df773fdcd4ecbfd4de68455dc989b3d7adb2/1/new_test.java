    public void close() throws BackendException {
        if (tx != null) tx.commit();
        if (null != store) store.close();
        if (null != manager) manager.close();
    }
    private WorkerCommand<Void, Transaction> beginTx( final GraphDatabaseService db )
    {
        return state -> db.beginTx();
    }
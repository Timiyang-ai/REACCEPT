private KernelTransaction beginTransaction( KernelTransaction.Type type, LoginContext loginContext, ClientConnectionInfo connectionInfo, long timeout )
    {
        try
        {
            availability.assertDatabaseAvailable();
            KernelTransaction kernelTx = database.getKernel().beginTransaction( type, loginContext, connectionInfo, timeout );
            kernelTx.registerCloseListener( txId -> statementContext.unbindTransactionFromCurrentThread() );
            statementContext.bindTransactionToCurrentThread( kernelTx );
            return kernelTx;
        }
        catch ( TransactionFailureException e )
        {
            throw new org.neo4j.graphdb.TransactionFailureException( e.getMessage(), e );
        }
    }
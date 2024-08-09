    @Test
    public void beginTransaction()
    {
        graphDatabaseFacade.beginTx();

        long timeout = Config.defaults().get( GraphDatabaseSettings.transaction_timeout ).toMillis();
        verify( spi ).beginTransaction( KernelTransaction.Type.explicit, AUTH_DISABLED, timeout );
    }
    @Test(expected = CloudRuntimeException.class)
    public void createFileSRTestNoSrRetrieveNoSrCreated() {
        Mockito.doReturn(null).when(xenserver625StorageProcessor).retrieveAlreadyConfiguredSrWithoutException(connectionMock, pathMock);
        Mockito.doReturn(null).when(xenserver625StorageProcessor).createNewFileSr(connectionMock, pathMock);

        xenserver625StorageProcessor.createFileSR(connectionMock, pathMock);
    }
    @Test(expected = CloudRuntimeException.class)
    public void updateNetworkACLItemTestUpdateDoesNotWork() throws ResourceUnavailableException {
        NetworkACLItemVO networkACLItemVOMock = new NetworkACLItemVO();
        networkACLItemVOMock.id = 1L;

        Mockito.doReturn(false).when(networkACLItemDaoMock).update(1L, networkACLItemVOMock);

        networkACLManagerImpl.updateNetworkACLItem(networkACLItemVOMock);
    }
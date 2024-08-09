    @Test
    public void validateNetworkACLItemTest() {
        Mockito.doNothing().when(networkAclServiceImpl).validateSourceStartAndEndPorts(networkAclItemVoMock);
        Mockito.doNothing().when(networkAclServiceImpl).validateSourceCidrList(networkAclItemVoMock);
        Mockito.doNothing().when(networkAclServiceImpl).validateProtocol(networkAclItemVoMock);

        networkAclServiceImpl.validateNetworkACLItem(networkAclItemVoMock);

        InOrder inOrder = Mockito.inOrder(networkAclServiceImpl);
        inOrder.verify(networkAclServiceImpl).validateSourceStartAndEndPorts(networkAclItemVoMock);
        inOrder.verify(networkAclServiceImpl).validateSourceCidrList(networkAclItemVoMock);
        inOrder.verify(networkAclServiceImpl).validateProtocol(networkAclItemVoMock);
    }
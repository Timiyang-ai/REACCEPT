    @Test
    public void updateNetworkACLItemTest() throws ResourceUnavailableException {
        Mockito.when(networkAclItemVoMock.getAclId()).thenReturn(networkAclMockId);
        Mockito.doReturn(networkAclItemVoMock).when(networkAclServiceImpl).validateNetworkAclRuleIdAndRetrieveIt(updateNetworkACLItemCmdMock);
        Mockito.doReturn(networkAclMock).when(networkAclManagerMock).getNetworkACL(networkAclMockId);
        Mockito.doNothing().when(networkAclServiceImpl).validateNetworkAcl(Mockito.eq(networkAclMock));
        Mockito.doNothing().when(networkAclServiceImpl).transferDataToNetworkAclRulePojo(Mockito.eq(updateNetworkACLItemCmdMock), Mockito.eq(networkAclItemVoMock), Mockito.eq(networkAclMock));
        Mockito.doNothing().when(networkAclServiceImpl).validateNetworkACLItem(networkAclItemVoMock);
        Mockito.doReturn(networkAclItemVoMock).when(networkAclManagerMock).updateNetworkACLItem(networkAclItemVoMock);

        networkAclServiceImpl.updateNetworkACLItem(updateNetworkACLItemCmdMock);

        InOrder inOrder = Mockito.inOrder(networkAclServiceImpl, networkAclManagerMock);
        inOrder.verify(networkAclServiceImpl).validateNetworkAclRuleIdAndRetrieveIt(updateNetworkACLItemCmdMock);
        inOrder.verify(networkAclManagerMock).getNetworkACL(networkAclMockId);
        inOrder.verify(networkAclServiceImpl).validateNetworkAcl(networkAclMock);
        inOrder.verify(networkAclServiceImpl).transferDataToNetworkAclRulePojo(Mockito.eq(updateNetworkACLItemCmdMock), Mockito.eq(networkAclItemVoMock), Mockito.eq(networkAclMock));
        inOrder.verify(networkAclServiceImpl).validateNetworkACLItem(networkAclItemVoMock);
        inOrder.verify(networkAclManagerMock).updateNetworkACLItem(networkAclItemVoMock);
    }
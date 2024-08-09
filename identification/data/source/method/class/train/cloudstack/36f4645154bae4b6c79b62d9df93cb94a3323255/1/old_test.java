    @Test(expected = InvalidParameterValueException.class)
    public void transferDataToNetworkAclRulePojoTestNumberOfAcltoBeUpdatedAlreadyInUse() {
        int aclNumberToUpdate = 1;
        Mockito.when(updateNetworkACLItemCmdMock.getNumber()).thenReturn(aclNumberToUpdate);
        Mockito.when(networkAclMock.getId()).thenReturn(networkAclMockId);
        Mockito.when(networkAclItemVoMock.getId()).thenReturn(100L);
        NetworkACLItemVO otherNetworkAclItemVoMock = Mockito.mock(NetworkACLItemVO.class);
        Mockito.when(otherNetworkAclItemVoMock.getId()).thenReturn(101L);
        Mockito.doReturn(otherNetworkAclItemVoMock).when(networkAclItemDaoMock).findByAclAndNumber(networkAclMockId, aclNumberToUpdate);

        networkAclServiceImpl.transferDataToNetworkAclRulePojo(updateNetworkACLItemCmdMock, networkAclItemVoMock, networkAclMock);
    }
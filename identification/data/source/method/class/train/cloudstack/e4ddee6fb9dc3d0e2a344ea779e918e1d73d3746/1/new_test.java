    @Test
    public void validateIcmpTypeAndCodeTestIcmpTypeNull() {
        Mockito.when(networkAclItemVoMock.getIcmpType()).thenReturn(null);

        networkAclServiceImpl.validateIcmpTypeAndCode(networkAclItemVoMock);
    }
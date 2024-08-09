@Test
  public void testListChangeRequests() {
    EasyMock.expect(dnsRpcMock.listChangeRequests(ZONE_INFO.getName(), EMPTY_RPC_OPTIONS))
        .andReturn(LIST_RESULT_OF_PB_CHANGES);
    EasyMock.replay(dnsRpcMock);
    dns = options.service(); // creates DnsImpl
    Page<ChangeRequest> changeRequestPage = dns.listChangeRequests(ZONE_INFO.getName());
    assertTrue(Lists.newArrayList(changeRequestPage.values()).contains(
        new ChangeRequest(dns, ZONE_INFO.getName(),
            new ChangeRequestInfo.BuilderImpl(CHANGE_REQUEST_COMPLETE))));
    assertTrue(Lists.newArrayList(changeRequestPage.values()).contains(
        new ChangeRequest(dns, ZONE_INFO.getName(),
            new ChangeRequestInfo.BuilderImpl(CHANGE_REQUEST_PARTIAL))));
    assertEquals(2, Lists.newArrayList(changeRequestPage.values()).size());
  }
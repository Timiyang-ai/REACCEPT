  @Test
  public void deleteRecord() throws Exception {
    server.enqueue(new MockResponse().setBody(deleteResourceRecordResponse));

    mockApi().deleteResourceRecord("ABCDEF");

    server.assertSoapBody(deleteResourceRecord);
  }
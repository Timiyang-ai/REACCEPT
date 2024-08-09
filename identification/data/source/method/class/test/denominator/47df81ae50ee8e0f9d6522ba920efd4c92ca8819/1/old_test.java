  @Test
  public void deleteRRPool() throws Exception {
    server.enqueue(new MockResponse().setBody(deleteLBPoolResponse));

    mockApi().deleteLBPool("AAAAAAAAAAAAAAAA");

    server.assertSoapBody(deleteLBPool);
  }
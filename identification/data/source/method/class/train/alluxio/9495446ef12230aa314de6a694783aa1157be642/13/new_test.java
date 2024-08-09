  @Test
  public void getRpcAddress() {
    when(mMasterProcess.getRpcAddress()).thenReturn(new InetSocketAddress("localhost", 8080));
    Response response = mHandler.getRpcAddress();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertEquals("Entry must be a String!", String.class, response.getEntity().getClass());
      String entry = (String) response.getEntity();
      assertEquals("\"localhost/127.0.0.1:8080\"", entry);
    } finally {
      response.close();
    }
  }
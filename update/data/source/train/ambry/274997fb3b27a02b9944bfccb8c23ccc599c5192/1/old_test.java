@Test
  public void testClose()
      throws IOException {
    List<RequestInfo> requestInfoList = new ArrayList<RequestInfo>();
    networkClient.close();
    try {
      networkClient.sendAndPoll(requestInfoList);
      Assert.fail("Polling after close should throw");
    } catch (IllegalStateException e) {
    }
  }
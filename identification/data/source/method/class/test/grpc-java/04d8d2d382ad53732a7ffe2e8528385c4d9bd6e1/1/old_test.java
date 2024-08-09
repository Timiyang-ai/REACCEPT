  @Test
  public void getServers_empty() {
    assertEquals(
        GetServersResponse.newBuilder().setEnd(true).build(),
        getServersHelper(0));
  }
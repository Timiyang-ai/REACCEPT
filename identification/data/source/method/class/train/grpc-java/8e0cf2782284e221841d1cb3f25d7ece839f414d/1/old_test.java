  @Test
  public void forNonError() {
    ConnectivityStateInfo info = ConnectivityStateInfo.forNonError(IDLE);
    assertEquals(IDLE, info.getState());
    assertEquals(Status.OK, info.getStatus());
  }
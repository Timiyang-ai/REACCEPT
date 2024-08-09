  @Test
  public void forTransientFailure() {
    ConnectivityStateInfo info = ConnectivityStateInfo.forTransientFailure(Status.UNAVAILABLE);
    assertEquals(TRANSIENT_FAILURE, info.getState());
    assertEquals(Status.UNAVAILABLE, info.getStatus());
  }
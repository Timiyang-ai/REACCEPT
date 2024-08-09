  @Test
  public void httpStatusToGrpcStatus_messageContainsHttpStatus() {
    assertTrue(GrpcUtil.httpStatusToGrpcStatus(500).getDescription().contains("500"));
  }
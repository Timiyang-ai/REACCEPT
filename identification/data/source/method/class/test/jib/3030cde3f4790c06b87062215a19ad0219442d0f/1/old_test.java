@Test
  public void testGetErrorCode_invalidErrorObject() {
    Mockito.when(responseException.getContent())
        .thenReturn("{\"type\":\"other\",\"message\":\"some other object\"}");
    try {
      ErrorResponseUtil.getErrorCode(responseException);
      Assert.fail();
    } catch (HttpResponseException ex) {
      Assert.assertSame(responseException, ex);
    }
  }
@Test
  public void testGetErrorCode_multipleErrors() {
    Mockito.when(responseException.getContent())
        .thenReturn(
            "{\"errors\":["
                + "{\"code\":\"MANIFEST_INVALID\",\"message\":\"message 1\",\"detail\":{}},"
                + "{\"code\":\"TAG_INVALID\",\"message\":\"message 2\",\"detail\":{}}"
                + "]}");
    try {
      ErrorResponseUtil.getErrorCode(responseException);
      Assert.fail();
    } catch (HttpResponseException ex) {
      Assert.assertSame(responseException, ex);
    }
  }
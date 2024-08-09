@Test
  public void testGetErrorCode_multipleErrors() {
    HttpResponseException httpResponseException =
        new HttpResponseException.Builder(
                HttpStatus.SC_BAD_REQUEST, "Bad Request", new HttpHeaders())
            .setContent(
                "{\"errors\":["
                    + "{\"code\":\"MANIFEST_INVALID\",\"message\":\"message 1\",\"detail\":{}},"
                    + "{\"code\":\"TAG_INVALID\",\"message\":\"message 2\",\"detail\":{}}"
                    + "]}")
            .build();
    try {
      ErrorResponseUtil.getErrorCode(httpResponseException);
      Assert.fail();
    } catch (HttpResponseException ex) {
      Assert.assertSame(httpResponseException, ex);
    }
  }
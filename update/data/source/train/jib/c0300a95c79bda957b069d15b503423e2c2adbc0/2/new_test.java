@Test
  public void testHandleHttpResponseException_dockerRegistry_tagInvalid()
      throws HttpResponseException {
    HttpResponseException exception = Mockito.mock(HttpResponseException.class);
    Mockito.when(exception.getStatusCode()).thenReturn(HttpStatus.SC_BAD_REQUEST);
    Mockito.when(exception.getContent())
        .thenReturn(
            "{\"errors\":[{\"code\":\"TAG_INVALID\","
                + "\"message\":\"manifest tag did not match URI\"}]}");
    try {
      testManifestPusher.handleHttpResponseException(exception);
      Assert.fail();

    } catch (RegistryErrorException ex) {
      Assert.assertThat(
          ex.getMessage(),
          CoreMatchers.containsString(
              "Registry may not support pushing OCI Manifest or "
                  + "Docker Image Manifest Version 2, Schema 2"));
    }
  }
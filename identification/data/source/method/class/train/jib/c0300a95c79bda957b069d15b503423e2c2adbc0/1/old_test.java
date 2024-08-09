@Test
  public void testHandleHttpResponseException_dockerRegistry_tagInvalid()
      throws HttpResponseException {
    HttpResponseException exception =
        new HttpResponseException.Builder(
                HttpStatus.SC_BAD_REQUEST, "Bad Request", new HttpHeaders())
            .setContent(
                "{\"errors\":[{\"code\":\"TAG_INVALID\","
                    + "\"message\":\"manifest tag did not match URI\"}]}")
            .build();
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
@Test
  public void processRequestTest() throws Exception {
    //rest request being null
    TestUtils.assertException(IllegalArgumentException.class, () -> securityService.processRequest(null).get(), null);
    TestUtils.assertException(IllegalArgumentException.class, () -> securityService.postProcessRequest(null).get(),
        null);

    // without callbacks
    RestMethod[] methods = new RestMethod[]{RestMethod.POST, RestMethod.GET, RestMethod.DELETE, RestMethod.HEAD};
    for (RestMethod restMethod : methods) {
      RestRequest restRequest = createRestRequest(restMethod, "/", null);
      securityService.processRequest(restRequest).get();
      securityService.postProcessRequest(restRequest).get();
    }

    // with GET sub resources
    for (RestUtils.SubResource subResource : RestUtils.SubResource.values()) {
      RestRequest restRequest = createRestRequest(RestMethod.GET, "/sampleId/" + subResource, null);
      securityService.processRequest(restRequest).get();
      securityService.postProcessRequest(restRequest).get();
    }

    // security service closed
    securityService.close();
    for (RestMethod restMethod : methods) {
      testExceptionCasesProcessRequest(createRestRequest(restMethod, "/", null),
          RestServiceErrorCode.ServiceUnavailable);
    }
  }
@Test
  public void processRequestTest() throws Exception {
    SecurityServiceCallback callback = new SecurityServiceCallback();
    //rest request being null
    try {
      securityService.processRequest(null, callback).get();
      Assert.fail("Should have thrown IllegalArgumentException ");
    } catch (IllegalArgumentException e) {
    }

    // without callbacks
    RestMethod[] methods = new RestMethod[]{RestMethod.POST, RestMethod.GET, RestMethod.DELETE, RestMethod.HEAD};
    for (RestMethod restMethod : methods) {
      RestRequest restRequest = createRestRequest(restMethod, "/", null);
      securityService.processRequest(restRequest, null).get();
    }

    // with callbacks
    callback = new SecurityServiceCallback();
    for (RestMethod restMethod : methods) {
      RestRequest restRequest = createRestRequest(restMethod, "/", null);
      securityService.processRequest(restRequest, callback).get();
      Assert.assertTrue("Callback should have been invoked", callback.callbackLatch.await(1, TimeUnit.SECONDS));
      Assert.assertNull("Exception should not have been thrown", callback.exception);
      callback.reset();
    }

    // with GET sub resources
    callback.reset();
    for (RestUtils.SubResource subResource : RestUtils.SubResource.values()) {
      RestRequest restRequest = createRestRequest(RestMethod.GET, "/sampleId/" + subResource, null);
      securityService.processRequest(restRequest, callback).get();
      Assert.assertTrue("Callback should have been invoked", callback.callbackLatch.await(1, TimeUnit.SECONDS));
      Assert.assertNull("Exception should not have been thrown", callback.exception);
      callback.reset();
    }

    // security service closed
    securityService.close();
    for (RestMethod restMethod : methods) {
      testExceptionCasesProcessRequest(createRestRequest(restMethod, "/", null),
          RestServiceErrorCode.ServiceUnavailable);
    }
  }
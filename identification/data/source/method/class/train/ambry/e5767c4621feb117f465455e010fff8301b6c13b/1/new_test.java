@Test
  public void getAsyncRequestResponseHandlerTest()
      throws InstantiationException {
    Properties properties = new Properties();
    VerifiableProperties verifiableProperties = new VerifiableProperties(properties);
    Router router = new InMemoryRouter(verifiableProperties);

    // Get response handler.
    AsyncRequestResponseHandlerFactory responseHandlerFactory =
        new AsyncRequestResponseHandlerFactory(1, restServerMetrics);
    RestResponseHandler restResponseHandler = responseHandlerFactory.getRestResponseHandler();
    assertNotNull("No RestResponseHandler returned", restResponseHandler);
    assertEquals("Did not receive an AsyncRequestResponseHandler instance",
        AsyncRequestResponseHandler.class.getCanonicalName(), restResponseHandler.getClass().getCanonicalName());

    BlobStorageService blobStorageService =
        new MockBlobStorageService(verifiableProperties, restResponseHandler, router);
    // Get request handler.
    AsyncRequestResponseHandlerFactory requestHandlerFactory =
        new AsyncRequestResponseHandlerFactory(1, restServerMetrics, blobStorageService);
    RestRequestHandler restRequestHandler = requestHandlerFactory.getRestRequestHandler();
    assertNotNull("No RestRequestHandler returned", restRequestHandler);
    assertEquals("Did not receive an AsyncRequestResponseHandler instance",
        AsyncRequestResponseHandler.class.getCanonicalName(), restRequestHandler.getClass().getCanonicalName());

    // make sure they are same instance
    assertEquals("Instances of AsyncRequestResponseHandler are not the same", restResponseHandler, restRequestHandler);

    // make sure the instance starts and shuts down OK.
    restRequestHandler.start();
    restRequestHandler.shutdown();
  }
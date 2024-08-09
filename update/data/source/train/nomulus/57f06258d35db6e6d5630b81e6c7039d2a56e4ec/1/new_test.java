@Test
  public void testHandleRequest_taskThrowsHttpException_atConstructionTime_getsHandledByHandler()
      throws Exception {
    when(req.getMethod()).thenReturn("GET");
    when(req.getRequestURI()).thenReturn("/failAtConstruction");
    when(requestAuthenticator.authorize(AUTH_PUBLIC.authSettings(), req))
        .thenReturn(Optional.of(AuthResult.create(AuthLevel.NONE)));

    handler.handleRequest(req, rsp);

    verify(rsp).sendError(503, "Fail at construction");
    assertMetric("/failAtConstruction", GET, AuthLevel.NONE, false);
  }
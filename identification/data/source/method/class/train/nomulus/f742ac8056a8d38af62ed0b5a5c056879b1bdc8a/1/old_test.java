@Test
  public void testHandleRequest_taskThrowsHttpException_atConstructionTime_getsHandledByHandler()
      throws Exception {
    when(req.getMethod()).thenReturn("GET");
    when(req.getRequestURI()).thenReturn("/failAtConstruction");
    handler.handleRequest(req, rsp, component);
    verify(rsp).sendError(503, "Fail at construction");
  }
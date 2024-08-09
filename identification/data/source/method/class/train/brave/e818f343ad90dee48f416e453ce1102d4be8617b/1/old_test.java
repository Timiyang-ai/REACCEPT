  @Test public void requestMatched_missingResourceMethodOk() {
    when(event.getContainerRequest()).thenReturn(request);
    when(request.getUriInfo()).thenReturn(uriInfo);

    eventParser.requestMatched(event, customizer);

    verifyNoMoreInteractions(customizer);
  }
  @Test public void route() {
    ExtendedUriInfo uriInfo = mock(ExtendedUriInfo.class);
    when(request.getUriInfo()).thenReturn(uriInfo);
    when(uriInfo.getBaseUri()).thenReturn(URI.create("/"));
    when(uriInfo.getMatchedTemplates()).thenReturn(Arrays.asList(
      new PathTemplate("/"),
      new PathTemplate("/items/{itemId}")
    ));

    assertThat(SpanCustomizingApplicationEventListener.route(request))
      .isEqualTo("/items/{itemId}");
  }
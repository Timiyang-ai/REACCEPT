protected void requestMatched(RequestEvent event, SpanCustomizer customizer) {
    Invocable i =
        event.getContainerRequest().getUriInfo().getMatchedResourceMethod().getInvocable();
    customizer.tag(RESOURCE_CLASS, i.getHandler().getHandlerClass().getSimpleName());
    customizer.tag(RESOURCE_METHOD, i.getHandlingMethod().getName());
  }
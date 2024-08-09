protected void requestMatched(RequestEvent event, SpanCustomizer customizer) {
    ResourceMethod method = event.getContainerRequest().getUriInfo().getMatchedResourceMethod();
    if (method == null) return; // This case is extremely odd as this is called on REQUEST_MATCHED!
    Invocable i = method.getInvocable();
    customizer.tag(RESOURCE_CLASS, i.getHandler().getHandlerClass().getSimpleName());
    customizer.tag(RESOURCE_METHOD, i.getHandlingMethod().getName());
  }
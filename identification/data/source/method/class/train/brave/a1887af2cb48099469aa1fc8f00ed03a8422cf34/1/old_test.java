  @Test public void newScope_retainsContext() {
    retainsContext(currentTraceContext.newScope(context));
  }
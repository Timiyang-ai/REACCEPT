  @Test
  public void statusFromCancelled_returnNullIfCtxNotCancelled() {
    Context context = Context.current();
    assertFalse(context.isCancelled());
    assertNull(statusFromCancelled(context));
  }
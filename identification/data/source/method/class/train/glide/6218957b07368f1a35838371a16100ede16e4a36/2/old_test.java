  @Test
  public void clear_notifiesTarget() {
    SingleRequest<List> request = builder.build();
    request.clear();

    verify(builder.target).onLoadCleared(anyDrawableOrNull());
  }
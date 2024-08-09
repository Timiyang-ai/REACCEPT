  @Test
  public void remove_clearsReference() {
    pendingSpans.getOrCreate(context, false);
    pendingSpans.remove(context);

    assertThat(pendingSpans.delegate).isEmpty();
    assertThat(pendingSpans.poll()).isNull();
  }
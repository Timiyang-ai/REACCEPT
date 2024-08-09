  @Test public void purgeable() {
    new RequestCreator(picasso, URI_1, 0).purgeable().into(mockImageViewTarget());
    verify(picasso).enqueueAndSubmit(actionCaptor.capture());
    assertThat(actionCaptor.getValue().request.purgeable).isTrue();
  }
  @Test
  public void removeAllFailure() throws StoreAccessException {
    strategy.removeAllFailure(asList(1, 2), accessException);
    @SuppressWarnings("unchecked")
    ArgumentCaptor<Iterable<Integer>> captor = ArgumentCaptor.forClass(Iterable.class);
    verify(store).obliterate(captor.capture());
    assertThat(captor.getValue()).contains(1, 2);
  }
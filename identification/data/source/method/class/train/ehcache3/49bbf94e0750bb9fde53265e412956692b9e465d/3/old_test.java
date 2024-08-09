  @Test
  public void removeAllFailure() throws Exception {
    List<Integer> entryList = Arrays.asList(1, 2);

    strategy.removeAllFailure(entryList, accessException);

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Iterable<Integer>> captor = ArgumentCaptor.forClass(Iterable.class);
    verify(store).obliterate(captor.capture());
    assertThat(captor.getValue()).contains(1, 2);
    verify(loaderWriter).deleteAll(entryList);
  }
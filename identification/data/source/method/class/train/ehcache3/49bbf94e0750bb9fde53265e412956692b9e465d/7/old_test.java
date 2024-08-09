  @Test
  public void getAllFailure_nothingFound() throws Exception {
    List<Integer> keys = Arrays.asList(1, 2);
    Map<Integer, Long> entries = new HashMap<>();
    keys.forEach(k -> entries.put(k, null));

    when(loaderWriter.loadAll(keys)).thenReturn(entries);

    assertThat(strategy.getAllFailure(keys, accessException)).isEqualTo(entries);

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Iterable<Integer>> captor = ArgumentCaptor.forClass(Iterable.class);
    verify(store).obliterate(captor.capture());
    assertThat(captor.getValue()).contains(1, 2);
    verify(loaderWriter).loadAll(keys);
  }
  @Test
  public void putAllFailure() throws Exception {
    List<MapEntry<Integer, Long>> entryList = Arrays.asList(entry(1, 1L), entry(2, 2L));
    Map<Integer, Long> entryMap = entryList.stream().collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    doNothing().when(loaderWriter).writeAll(argThat(containsAllMatcher(entryList)));

    strategy.putAllFailure(entryMap, accessException);

    @SuppressWarnings("unchecked")
    ArgumentCaptor<Iterable<Integer>> captor = ArgumentCaptor.forClass(Iterable.class);
    verify(store).obliterate(captor.capture());
    assertThat(captor.getValue()).contains(1, 2);
    verify(loaderWriter).writeAll(argThat(containsAllMatcher(entryList)));
  }
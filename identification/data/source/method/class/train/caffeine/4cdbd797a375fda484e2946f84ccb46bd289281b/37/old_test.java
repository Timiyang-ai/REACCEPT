  @Test
  public void loadAll() {
    loadAllSupplier = () -> ImmutableMap.of(1, -1, 2, -2, 3, -3);
    Map<Integer, Integer> result = jcacheLoading.getAll(ImmutableSet.of(1, 2, 3));
    assertThat(result, is(equalTo(loadAllSupplier.get())));
  }
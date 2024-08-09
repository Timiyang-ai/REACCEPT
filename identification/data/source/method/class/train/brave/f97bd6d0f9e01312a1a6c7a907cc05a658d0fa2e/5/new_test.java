@Test
  public void remove_whenSomeReferencesAreCleared() {
    map.getOrCreate(context, false);
    pretendGCHappened();
    map.remove(context);

    assertThat(map.delegate.keySet()).extracting(o -> ((Reference) o).get())
        .hasSize(1)
        .containsNull();
  }
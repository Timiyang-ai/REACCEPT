  @Test
  public void size() {
    assertEquals(6, SortedSets.union(abc, def).size());
    assertEquals(6, SortedSets.union(def, abc).size());
    assertEquals(6, SortedSets.union(abc, abcdef).size());
    assertEquals(6, SortedSets.union(abcdef, abc).size());
    assertEquals(3, SortedSets.union(abc, empty).size());
    assertEquals(3, SortedSets.union(empty, abc).size());
    assertEquals(0, SortedSets.union(empty, empty).size());
  }
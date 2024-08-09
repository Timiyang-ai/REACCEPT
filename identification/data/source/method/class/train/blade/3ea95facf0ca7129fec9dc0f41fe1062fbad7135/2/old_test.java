  @Test
  public void newSets() {
    final Object[] values = {};
    assertEquals(new HashSet(), CollectionKit.newSets(values));
  }
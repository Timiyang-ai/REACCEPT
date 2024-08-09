  @Test
  public void clear() {
    TableSlice slice = new TableSlice(source, Selection.withRange(0, source.rowCount()));
    slice.clear();
    assertTrue(slice.isEmpty());
    assertFalse(source.isEmpty());
  }
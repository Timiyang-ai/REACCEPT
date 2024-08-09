  @Test
  public void reduce() throws Exception {
    source = Table.read().csv("../data/bush.csv");
    TableSlice slice = new TableSlice(source, Selection.with(2));
    assertEquals(58.0, slice.reduce("approval", sum), 0.0001);
  }
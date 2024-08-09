  @Test
  public void columnNames() throws IOException {
    Table table = Table.read().csv("../data/bush.csv");
    Row row = new Row(table);
    assertEquals(table.columnNames(), row.columnNames());
  }
  @Test
  public void aggregate() {
    TableSliceGroup group = StandardTableSliceGroup.create(table, table.categoricalColumn("who"));
    Table aggregated = group.aggregate("approval", exaggerate);
    assertEquals(aggregated.rowCount(), group.size());
  }
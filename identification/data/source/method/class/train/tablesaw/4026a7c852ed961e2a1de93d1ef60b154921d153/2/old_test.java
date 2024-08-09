  @Test
  void stepWithRows() throws Exception {
    Table t =
        Table.read().csv(CsvReadOptions.builder("../data/bush.csv").minimizeColumnSizes()).first(6);

    final int sum1 = (int) t.shortColumn("approval").sum();

    RowConsumer rowConsumer = new RowConsumer();
    t.stepWithRows(rowConsumer, 3);
    assertEquals(sum1, rowConsumer.getSum());
  }
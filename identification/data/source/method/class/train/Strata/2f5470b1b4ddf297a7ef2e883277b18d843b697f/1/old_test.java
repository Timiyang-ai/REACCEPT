  @Test
  public void getColumnTypes() {
    ArrayTable<Integer, Integer, Result<?>> table = ArrayTable.create(INDICES, INDICES);
    table.put(0, 0, Result.success(1));
    table.put(0, 1, Result.success("abc"));
    table.put(1, 0, Result.success(2));
    table.put(1, 1, Result.success("def"));

    List<Class<?>> columnTypes = TradeReportFormatter.INSTANCE.getColumnTypes(report(table));
    assertThat(columnTypes).isEqualTo(ImmutableList.of(Integer.class, String.class));
  }
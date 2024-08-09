private void write(ProfileMeasurement m, List<Object> groups) {

    byte[] rowKey = rowKeyBuilder.rowKey(m, groups);
    ColumnList cols = columnBuilder.columns(m);

    hbaseClient.addMutation(rowKey, cols, Durability.SKIP_WAL);
    hbaseClient.mutate();
  }
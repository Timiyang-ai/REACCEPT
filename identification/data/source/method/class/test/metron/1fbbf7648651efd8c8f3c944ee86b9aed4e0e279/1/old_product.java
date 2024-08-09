private void write(ProfileMeasurement m, List<Object> groups) {

    byte[] rowKey = rowKeyBuilder.rowKey(m, groups);
    ColumnList cols = columnBuilder.columns(m);

    List<Mutation> mutations = hbaseClient.constructMutationReq(rowKey, cols, Durability.SKIP_WAL);
    hbaseClient.batchMutate(mutations);
  }
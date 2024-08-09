@Override
  public Status insert(String table, String key,
      HashMap<String, ByteIterator> values) {

    try {
      Insert insertStmt = QueryBuilder.insertInto(table);

      // Add key
      insertStmt.value(YCSB_KEY, key);

      // Add fields
      for (Map.Entry<String, ByteIterator> entry : values.entrySet()) {
        Object value;
        ByteIterator byteIterator = entry.getValue();
        value = byteIterator.toString();

        insertStmt.value(entry.getKey(), value);
      }

      insertStmt.setConsistencyLevel(writeConsistencyLevel);

      if (debug) {
        System.out.println(insertStmt.toString());
      }

      ResultSet rs = session.execute(insertStmt);

      return Status.OK;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Status.ERROR;
  }